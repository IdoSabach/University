package mmn12;

/**
 * The Apartment class represents an apartment with details such as the number
 * of rooms, area, price, tenant, and rental dates.
 * 
 * @author Ido Sabach
 * @version 1.0
 * @since 08/01/2024
 */
public class Apartment {

  private int _noOfRooms;
  private double _area;
  private double _price;
  Person _tenant;
  Date _rentalStartDate;
  Date _rentalEndDate;

  private final int DEFAULT_ROOM = 3;
  private final int DEFAULT_NUM_FOR_ADD_DATE = 1;
  private final double DEFAULT_AREA = 80;
  private final double DEFAULT_PRICE = 5000;

  /**
   * Apartment Constructor
   *
   * @param noOfRooms - the number of rooms the apartment has
   * @param area - the apartment's area
   * @param price - monthly price to rent the apartment
   * @param p - the person renting the apartment
   * @param startDay - starting day of apartment rental
   * @param startMonth - starting month of apartment rental
   * @param startYear - starting year of apartment rental
   * @param endDay - last day of apartment rental
   * @param endMonth - last month of apartment rental
   * @param endYear - last year of apartment rental
   */
  public Apartment(int room, double area, double price, Person tenant,int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear) {
      if (room <= 0) {
          _noOfRooms = DEFAULT_ROOM;
      } else {
          _noOfRooms = room;
      }

      if (area <= 0) {
          _area = DEFAULT_AREA;
      } else {
          _area = area;
      }

      if (price <= 0) {
          _price = DEFAULT_PRICE;
      } else {
          _price = price;
      }

      _tenant = new Person(tenant);
      _rentalStartDate = new Date(startDay,startMonth,startYear);
      _rentalEndDate = new Date(endDay,endMonth,endYear);

      if (_rentalEndDate.after(_rentalStartDate)) {
          _rentalEndDate = new Date(endDay,endMonth,endYear);
      } else {
          _rentalEndDate = _rentalEndDate.addYearsToDate(DEFAULT_NUM_FOR_ADD_DATE);
      }
  }

  /**
   * Copy Constructor
   *
   * @param other - the apartment to be copied
   */
  public Apartment(Apartment other) {
      this._noOfRooms = other._noOfRooms;
      this._area = other._area;
      this._price = other._price;
      this._tenant = new Person(other._tenant);
      this._rentalStartDate = new Date(other._rentalStartDate);
      this._rentalEndDate = new Date(other._rentalEndDate);
  }

  /**
   * Gets the number of rooms the apartment has
   *
   * @return the number of rooms the apartment has
   */
  public int getNoOfRooms() {
      return _noOfRooms;
  }

  /**
   * Gets the area of the apartment.
   *
   * @return The area of the apartment.
   */
  public double getArea() {
      return _area;
  }

  /**
   * Gets the price of the apartment.
   *
   * @return The price of the apartment.
   */
  public double getPrice() {
      return _price;
  }

  /**
   * Gets the tenant renting the apartment
   *
   * @return the tenant renting the apartment
   */
  public Person getTenant() {
      return new Person(_tenant);
  }

  /**
   * Gets the rental start date.
   *
   * @return The rental start date.
   */
  public Date getRentalStartDate() {
      return new Date(_rentalStartDate);
  }

  /**
   * Gets the rental end date.
   *
   * @return The rental end date.
   */
  public Date getRentalEndDate() {
      return new Date(_rentalEndDate);
  }

  /**
   * Sets the apartment's number of rooms (only if the new value is positive)
   *
   * @param noOfRooms - new number of rooms
   */
  public void setNoOfRooms(int num) {
      if (num <= 0) {
          _noOfRooms = DEFAULT_ROOM;
      } else {
          _noOfRooms = num;
      }
  }

  /**
   * Sets the apartment's area (only if the new value is positive)
   *
   * @param area - apartment's new area
   */
  public void setArea(double area) {
      if (area <= 0) {
          _area = DEFAULT_AREA;
      } else {
          _area = area;
      }
  }

  /**
   * Sets the apartment's price (only if the new value is positive)
   *
   * @param price - apartment's new price

   */
  public void setPrice(double price) {
      if (price <= 0) {
          _price = DEFAULT_PRICE;
      } else {
          _price = price;
      }
  }

  /**
   * Sets the apartment's tenant
   *
   * @param tenant - apartment's new tenant
   */
  public void setTenant(Person p) {
      _tenant = new Person(p);
  }

  /**
   * Sets the apartment's rental start date (only if the new rental start date is before the current rental end date)
   *
   * @param rentalStartDate - apartment's new rental start date
   */
  public void setRentalStartDate(Date d) {
      _rentalStartDate = new Date(d);
  }

  /**
   * Sets the apartment's rental end date (only if the new rental end date is after the current rental start date)
   *
   * @param rentalEndDate - apartment's new rental end date
   */
  public void setRentalEndDate(Date d) {
      if (d.after(getRentalStartDate())) {
          _rentalEndDate = new Date(d);
      } else {
          _rentalEndDate = d.addYearsToDate(DEFAULT_NUM_FOR_ADD_DATE);
      }
  }

  /**
   * Returns a String that represents this Apartment
   *
   * @return a String that represents this Apartment
   */
  public String toString() {
      return "Number of rooms: " + _noOfRooms + "\n" +
      "Area: " + _area + "\n" +
      "Price: " + _price + " NIS" + "\n" +
      "Tenant name: " + _tenant.getName() + "\n" +
      "Rental start date: " + _rentalStartDate.toString() + "\n" +
      "Rental end date: " + _rentalEndDate.toString();
  }

  /**
   * Checks if the current and other apartments are equal by all the apartment attributes
   *
   * @param other - an apartment object
   * @return true if the two objects are equal. Otherwise, returns false
   */
  public boolean equals(Apartment other) {
      if (this._noOfRooms == other._noOfRooms &&
      this._area == other._area &&
      this._price == other._price &&
      this._tenant.equals(other._tenant) &&
      this._rentalStartDate.equals(other._rentalStartDate) &&
      this._rentalEndDate.equals(other._rentalEndDate)) {
          return true;
      } else {
          return false;
      }
  }

  /**
   * Extends the rental period by additional years(only if the years value is positive)
   *
   * @paramyears - the number of years to extend the lease
   */
  public void extendRentalPeriod(int year) {
      if (year <= 0) {
          return;
      }
      Date newEndDate = _rentalEndDate.addYearsToDate(year);
      _rentalEndDate = new Date(newEndDate);
  }

  /**
   * Computes the number of days left between a given date and the end of rental date. If the given date comes after the end of rental date, returns -1.
   *
   * @param d - a date object
   * @return the number of days left between a given date and the end of rental date
   */
  public int daysLeft(Date d) {
      if (d.after(_rentalEndDate)) {
          return -1;
      }
      return _rentalEndDate.difference(d);
  }

  /**
   * Checks if the apartment's tenant can be replaced and update apartment attributes accordingly ( if the tenant is younger, the rental end date ends up to 90 days after the new rental start date and the new price is at least the current rental's price)
   *
   * @param startDate       The new rental start date.
   * @param candidateTenant The new tenant.
   * @param price           The new price.
   * @return true if the tenant has been changed
   */
  public boolean changeTenant(Date startDate, Person p, double price) {
      if (p.getDateOfBirth().after(getTenant().getDateOfBirth()) &&
      price >= getPrice() &&
      startDate.difference(this._rentalEndDate)<=90) {
          setTenant(p);
          setPrice(price);
          setRentalStartDate(startDate);
          setRentalEndDate(startDate.addYearsToDate(1));
          return true;
      }

      return false;

  }

}