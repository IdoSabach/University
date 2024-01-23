package mmn12;

/**
 * The Date class represents a date in the Gregorian calendar.
 * 
 * @author Ido Sabach
 * @version 1.0
 * @since 08/01/2024
 */
public class Date {

  private int _day;
  private int _month;
  private int _year;

  private final int DEFAULT_DAY = 1;
  private final int DEFAULT_MONTH = 1;
  private final int DEFAULT_YEAR = 2000;

  private final int JANUARY = 1;
  private final int FEBRUARY = 2;
  private final int MARCH = 3;
  private final int APRIL = 4;
  private final int MAY = 5;
  private final int JUNE = 6;
  private final int JULY = 7;
  private final int AUGUST = 8;
  private final int SEPTEMBER = 9;
  private final int OCTOBER = 10;
  private final int NOVEMBER = 11;
  private final int DECEMBER = 12;
  
  private final int MIN_YEAR = 1000;
  private final int MAX_YEAR = 9999;
  
  private final int MIN_MONTH = 1;
  private final int MAX_MONTH = 12;
  
  private final int MIN_DAY_MONTH = 1;
  private final int MAX_LONG_MONTH = 31;
  private final int MAX_SHORT_MONTH = 30;
  private final int MAX_FEB_MONTH = 28;
  private final int MAX_LEAP_MONTH = 29;

  /**
   * Date constructor - If the given date is valid - creates a new Date object, otherwise creates the date 01/01/2000.
   *
   * @param day   The day of the month (1-31).
   * @param month The month (1-12).
   * @param year  The year (4 digit).
   */
  public Date(int day, int month, int year) {
      if (year >= MIN_YEAR && year <= MAX_YEAR) {
          _year = year;
          if (month >= MIN_MONTH && month <= MAX_MONTH) {
              _month = month;
              switchCaseFunc(day, month, year);
          } else {
              defaultValues();
          }
      } else {
          defaultValues();
      }
  }

  /**
   * Copy constructor
   *
   * @param other - the date object to copied.
   */
  public Date(Date other) {
      this._day = other._day;
      this._month = other._month;
      this._year = other._year;
  }

  /**
   * Gets the day
   *
   * @return the day of this date
   */
  public int getDay() {
      return _day;
  }

  /**
   * Gets the month.
   *
   * @return the month of this date
   */
  public int getMonth() {
      return _month;
  }

  /**
   * Gets the year.
   *
   * @return the year of this date
   */
  public int getYear() {
      return _year;
  }

  /**
   * Sets the day (only if date remains valid)
   *
   * @param dayToSet - the new day value
   */
  public void setDay(int dayToSet) {
      if (_month == JANUARY || _month == MARCH || _month == MAY || _month == JULY || _month == AUGUST || _month == OCTOBER || _month == DECEMBER) {
          if (dayToSet >= MIN_DAY_MONTH && dayToSet <= MAX_LONG_MONTH) {
              _day = dayToSet;
          }
      } else if (_month == APRIL || _month == JUNE || _month == SEPTEMBER || _month == NOVEMBER) {
          if (dayToSet >= MIN_DAY_MONTH && dayToSet <= MAX_SHORT_MONTH) {
              _day = dayToSet;
          }
      } else if (_month == FEBRUARY) {
          if (isLeapYear(_year)) {
              if (dayToSet >= MIN_DAY_MONTH && dayToSet <= MAX_LEAP_MONTH) {
                  _day = dayToSet;
              }
          } else {
              if (dayToSet >= MIN_DAY_MONTH && dayToSet <= MAX_FEB_MONTH) {
                  _day = dayToSet;
              }
          }

      }
  }

  /**
   * Sets the month (only if date remains valid)
   *
   * @param monthToSet - the new month value
   */
  public void setMonth(int monthToSet) {
      if (monthToSet >= MIN_MONTH && monthToSet <= MAX_MONTH) {
          _month = monthToSet;
      }
  }

  /**
   * Sets the year (only if date remains valid)
   *
   * @param yearToSet - the new year value
   */
  public void setYear(int yearToSet) {
      if (yearToSet >= MIN_YEAR && yearToSet <= MAX_YEAR) {
          _year = yearToSet;
      }
  }

  private void defaultValues() {
      _day = DEFAULT_DAY;
      _month = DEFAULT_MONTH;
      _year = DEFAULT_YEAR;
  }

  /**
   * Checks if the current year is a leap year.
   *
   * @param y The year to check.
   * @return True if it's a leap year, false otherwise.
   */
  private boolean isLeapYear(int y) {
      return (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0) ? true : false;
  }

  private void switchCaseFunc(int day, int month, int year) {
      switch (month) {
          case JANUARY:
          case MARCH:
          case MAY:
          case JULY:
          case AUGUST: 
          case OCTOBER:
          case DECEMBER: {
                  if (day >= MIN_DAY_MONTH && day <= MAX_LONG_MONTH) {
                      _day = day;
                  } else {
                      defaultValues();
                  }
                  break;
              }
          case APRIL:
          case JUNE:
          case SEPTEMBER:
          case NOVEMBER: {
                  if (day >= MIN_DAY_MONTH && day <= MAX_SHORT_MONTH) {
                      _day = day;
                  } else {
                      defaultValues();
                  }
                  break;
              }
          case FEBRUARY: {
                  if (isLeapYear(year)) {
                      if (day >= MIN_DAY_MONTH && day <= MAX_LEAP_MONTH) {
                          _day = day;
                      } else {
                          defaultValues();
                      }
                  } else {
                      if (day >= MIN_DAY_MONTH && day <= MAX_FEB_MONTH) {
                          _day = day;
                      } else {
                          defaultValues();
                      }
                  }
                  break;
              }
      }
  }

  /**
   * Checks if two dates are the same
   *
   * @param other - the date to compare this date to
   * @return true if the dates are the same
   */
  public boolean equals(Date other) {
      if (other == null) {
          return false;
      }
      return this._year == other._year &&
      this._month == other._month &&
      this._day == other._day;
  }

  /**
   * Checks if this date comes before another date
   *
   * @param other - date to compare this date to
   * @return true if this date is before the other date
   */
  public boolean before(Date other) {
      if (this._year < other._year) {
          return true;
      } else if (this._year == other._year && this._month < other._month) {
          return true;
      } else if (this._year == other._year && this._month == other._month && this._day < other._day) {
          return true;
      } else {
          return false;
      }
  }

  /**
   * Checks if this date comes after another date
   *
   * @param other - date to compare this date to
   * @return true if this date is after the other date
   */
  public boolean after(Date other) {
      return !before(other);
  }

  // computes the day number since the beginning of the Christian counting of
  // years
  private int calculateDate(int day, int month, int year) {
      if (month < 3) {
          year--;
          month = month + 12;
      }
      return 365 * year + year / 4 - year / 100 + year / 400 + ((month + 1) * 306) / 10 + (day - 62);
  }

  /**
   * Calculates the difference in days between two dates
   *
   * @param other - the date to calculate the difference between
   * @return the number of days between the dates (non negative value)
   */
  public int difference(Date other) {
      int num1 = calculateDate(this._day, this._month, this._year);
      int num2 = calculateDate(other._day, other._month, other._year);
      int num = Math.abs(num1 - num2);
      return num;
  }

  /**
   * Returns a String that represents this date
   *
   * @return a String that represents this date in the following format: day (2 digits) / month(2 digits) / year (4 digits) for example: 02/03/1998
   */
  public String toString() {
      String str;
      if (this._day < 10 && this._month < 10) {
          str = "0" + this._day + "/" + "0" + this._month + "/" + this._year;
      } else if (this._day < 10) {
          str = "0" + this._day + "/" + this._month + "/" + this._year;
      } else if (this._month < 10) {
          str = this._day + "/" + "0" + this._month + "/" + this._year;
      } else {
          str = this._day + "/" + this._month + "/" + this._year;
      }
      return str;
  }

  /**
   * Returns a new Date which is the current date after adding a number of years to it (the current date does not change)
   *
   * @param num - the number of years to add (a positive number)
   * @return the new date: this date plus num years
   */
  public Date addYearsToDate(int num) {
      Date newDate = new Date(this._day, this._month, this._year);
      newDate._year += num;
      if (this._month == FEBRUARY && this._day == MAX_LEAP_MONTH && !isLeapYear(newDate._year)) {
          newDate._day = MAX_FEB_MONTH;
      }
      if(this._month == FEBRUARY && this._day == MAX_FEB_MONTH && isLeapYear(newDate._year)){
          newDate._day = MAX_LEAP_MONTH;
      }
      return newDate;
  }

}
