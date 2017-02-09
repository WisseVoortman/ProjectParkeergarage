import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/*
 * the data and the methods to work with it.
 */
public class Model implements Runnable{

	private static final String AD_HOC = "1";
	private static final String PASS = "2";

	private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    
    
    private int day = 0;
    private int hour = 0;
    private int minute = 0;
    
    private int pricePerMinute = 10; // in cents
    private int revenue;
    private int toMakeRevenue;
    
    private int tickPause = 100;
    private boolean simulatorRunning;
    
    private List<AbstractView> views;

    int weekDayArrivals= 100; // average number of arriving cars per hour
    int weekendArrivals = 200; // average number of arriving cars per hour
    int weekDayPassArrivals= 50; // average number of arriving cars per hour
    int weekendPassArrivals = 5; // average number of arriving cars per hour
    
    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute

    private int numberOfFloors = 3;
    private int numberOfRows = 6;
    private int numberOfPlaces = 30;
    private int numberOfOpenSpots = numberOfFloors*numberOfRows*numberOfPlaces;

    private Car[][][] cars;
	
    public Model() {
    	entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();

        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        views=new ArrayList<AbstractView>();
    }
    
    public void start() {
    	// automaticaly calls run();
		new Thread(this).start();
	}
    
    public void run() {
    	//initial given run method based on a number of ticks
//        for (int i = 0; i < 13000; i++) {
//            tick();
//        }
    	
    	// my implementation of running bases on while true
    	simulatorRunning = true;
    	while(simulatorRunning){
    		tick();
    	}
    }
    
    public void stop(){
    	simulatorRunning = false;
    	updateViews();
    }
    
    public void addView(AbstractView view) {
		views.add(view);
	}

    public void tick() {
    	advanceTime();
    	handleExit();
    	updateViews();
    	// Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	handleEntrance();
    }
    
    public void ticks() {
    	for (int i = 0; i < 60; i++){
    		
    		tick();
    	}
    }

    private void advanceTime(){
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

    }

    private void handleEntrance(){
    	carsArriving();
    	carsEntering(entrancePassQueue);
    	carsEntering(entranceCarQueue);  	
    }
    
    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }
    
    public void updateViews(){
    	carAction();
        // Update the car park view.
        for( AbstractView v : views ) {
            v.updateView();
        }
    }
    
    private void carsArriving(){
    	int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);    	
    	numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);    	
    }

    private void carsEntering(CarQueue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
    	while (queue.carsInQueue()>0 && 
    			getNumberOfOpenSpots()>0 &&
    			i<enterSpeed) {
            Car car = queue.removeCar();
            
            // add the price to the total Revenue
            if(car.getHasToPay()){
            	//TODO for car payment
                //check how long the car stayed
                  int carStayDuration = car.getStayMinutes(); 
                  
                  //calculate the price the car has to pay!
                  int carPrice = carStayDuration * pricePerMinute;
            	this.toMakeRevenue = toMakeRevenue + carPrice;
            }
            
            
            Location freeLocation = getFirstFreeLocation();
            setCarAt(freeLocation, car);
            i++;
        }
    }
    
    private void carsReadyToLeave(){
        // Add leaving cars to the payment queue.
        Car car = getFirstLeavingCar();
        while (car!=null) {
        	if (car.getHasToPay()){
	            car.setIsPaying(true);
	            paymentCarQueue.addCar(car);
        	}
        	else {
        		carLeavesSpot(car);
        	}
            car = getFirstLeavingCar();
        }
    }

    private void carsPaying(){
        // Let cars pay.
    	int i=0;
    	while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            Car car = paymentCarQueue.removeCar();
            // TODO Handle payment.
            //check how long the car stayed
            int carStayDuration = car.getStayMinutes(); 
            
            //calculate the price the car has to pay!
            int carPrice = carStayDuration * pricePerMinute;
            // add the price to the total Revenue
            this.revenue = revenue + carPrice;
            
            // remove the price from the toMakeRevenue
            this.toMakeRevenue = toMakeRevenue - carPrice;
            
            carLeavesSpot(car);
            i++;
    	}
    }
    
    private void carsLeaving(){
        // Let cars leave.
    	int i=0;
    	while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
            i++;
    	}	
    }
    
    private int getNumberOfCars(int weekDay, int weekend){
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);	
    }
    
    private void addArrivingCars(int numberOfCars, String type){
        // Add the cars to the back of the queue.
    	switch(type) {
    	case AD_HOC: 
            for (int i = 0; i < numberOfCars; i++) {
            	entranceCarQueue.addCar(new AdHocCar());
            }
            break;
    	case PASS:
            for (int i = 0; i < numberOfCars; i++) {
            	entrancePassQueue.addCar(new ParkingPassCar());
            }
            break;	            
    	}
    }
    
    private void carLeavesSpot(Car car){
    	removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public int getNumberOfOpenSpots(){
        return numberOfOpenSpots;
    }

    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            numberOfOpenSpots--;
            return true;
        }
        return false;
    }

    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        numberOfOpenSpots++;
        return car;
    }

    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    public int getCars( String typeName ) {
        int count = 0;
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) != null ) {
                        if( getCarAt(location).getClass().getName().equals(typeName) ) count++;
                    }
                }
            }
        }
        return count;
    }

    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }

    /*
     * allowing a car to do something by checking every parking spot for a car, if there is a car call the carTick() method.
     */
    public void carAction() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null) {
                        car.carRemoveMinute();
                    }
                }
            }
        }
    }

    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }
    
    public boolean getSimulatorRunning() {
    	return simulatorRunning;
    }
    
    public String getDayString() {
    	if (day == 0) {
    		
    		return "Maandag";
    	}
    	if (day == 1) {
    		return "dinsdag";
    	}
    	if (day == 2) {
    		return "Woensdag";
    	}
    	if (day == 3) {
    		return "Donderdag";
    	}
    	if (day == 4) {
    		return "Vrijdag";
    	}
    	if (day == 5) {
    		return "Zaterdag";
    	}
    	if (day == 6) {
    		return "Zondag";
    	}
    	
    	return "";
    }
    
    public String getHourString() {
    	if (hour < 10) {
    		return "0" + Integer.toString(hour) ;
    	} 
    	else {
    		return Integer.toString(hour);
    	}
    }
    
    public String getMinuteString() {
    	if (minute < 10) {
    		return "0" + Integer.toString(minute);
    	} 
    	else {
    		return Integer.toString(minute);
    	}
    }
    
    public CarQueue getEntranceCarQueue() {
    	return entranceCarQueue;
    }
    
    public CarQueue getEntrancePassQueue() {
    	return entrancePassQueue;
    }
    
    public CarQueue getPaymentCarQueue() {
    	return paymentCarQueue;
    }
    
    public CarQueue getExitCarQueue() {
    	return exitCarQueue;
    }
    
    public String getRevenueString() {
    	int euro = revenue / 100;
    	int cent = revenue % 100;
    	
    	
    	String revenueString = "€ " + Integer.toString(euro) + "." + Integer.toString(cent);
    	
    	return revenueString;
    }
    
    public String getToMakeRevenueString() {
    	int euro = toMakeRevenue / 100;
    	int cent = toMakeRevenue % 100;
    	
    	
    	String toMakeRevenueString = "€ " + Integer.toString(euro) + "." + Integer.toString(cent);
    	
    	return toMakeRevenueString;
    }

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public void setDay(String day) {
        switch(day.toLowerCase()) {
            case "maandag":
                this.day = 0;
                break;
            case "dinsdag":
                this.day = 1;
                break;
            case "woensdag":
                this.day = 2;
                break;
            case "donderdag":
                this.day = 3;
                break;
            case "vrijdag":
                this.day = 4;
                break;
            case "zaterdag":
                this.day = 5;
                break;
            case "zondag":
                this.day = 6;
                break;
            default:
                this.day = 0;
                break;
        }
    }

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getPricePerMinute() {
		return pricePerMinute;
	}

	public void setPricePerMinute(int pricePerMinute) {
		this.pricePerMinute = pricePerMinute;
	}

	public int getTickPause() {
		return tickPause;
	}

	public void setTickPause(int tickPause) {
		this.tickPause = tickPause;
	}

	public int getWeekDayArrivals() {
		return weekDayArrivals;
	}

	public void setWeekDayArrivals(int weekDayArrivals) {
		this.weekDayArrivals = weekDayArrivals;
	}

	public int getWeekendArrivals() {
		return weekendArrivals;
	}

	public void setWeekendArrivals(int weekendArrivals) {
		this.weekendArrivals = weekendArrivals;
	}

	public int getWeekDayPassArrivals() {
		return weekDayPassArrivals;
	}

	public void setWeekDayPassArrivals(int weekDayPassArrivals) {
		this.weekDayPassArrivals = weekDayPassArrivals;
	}

	public int getWeekendPassArrivals() {
		return weekendPassArrivals;
	}

	public void setWeekendPassArrivals(int weekendPassArrivals) {
		this.weekendPassArrivals = weekendPassArrivals;
	}

	public int getEnterSpeed() {
		return enterSpeed;
	}

	public void setEnterSpeed(int enterSpeed) {
		this.enterSpeed = enterSpeed;
	}

	public int getPaymentSpeed() {
		return paymentSpeed;
	}

	public void setPaymentSpeed(int paymentSpeed) {
		this.paymentSpeed = paymentSpeed;
	}

	public int getExitSpeed() {
		return exitSpeed;
	}

	public void setExitSpeed(int exitSpeed) {
		this.exitSpeed = exitSpeed;
	}

	public void setNumberOfFloors(int numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}

	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	public void setNumberOfPlaces(int numberOfPlaces) {
		this.numberOfPlaces = numberOfPlaces;
	}
}
