package mmn12;

/**
 * The Person class represents a person with a name, ID, and date of birth.
 * 
 * @author Ido Sabach
 * @version 1.0
 * @since 08/01/2024
 */
public class Person {

  private String _name;
  private String _id;
  Date _dateOfBirth;

  private final int MAX_ID = 9;

  /**
   * Person Constructor
   *
   * @param name - the person's name. If name is an empty string, assign the default string "Someone"
   * @param day - the day of the Person's date of birth
   * @param month - the month of the Person's date of birth
   * @param year - the year of the Person's date of birth
   * @param id - the person's id. If id doesn't contain 9 digits, assign the default string "000000000"
   */
  public Person(String name,int day , int month, int year, String id) {
      if (name == null) {
          _name = "Someone";
      } else {
          _name = name;
      }

      if (id.length() != MAX_ID) {
          _id = "000000000";
      } else {
          _id = id;
      }

      _dateOfBirth = new Date(day,month,year);
  }

  /**
   * Copy Constructor
   *
   * @param other - the person to be copied
   */
  public Person(Person other) {
      this._name = other._name;
      this._id = other._id;
      this._dateOfBirth = new Date(other._dateOfBirth);
  }

  /**
   * Gets the person's name
   *
   * @return the person's name
   */
  public String getName() {
      return _name;
  }

  /**
   * Gets the person's id
   *
   * @return person's id
   */
  public String getId() {
      return _id;
  }

  /**
   * Gets the person's date of birth
   *
   * @return person's date of birth
   */
  public Date getDateOfBirth() {
      return new Date(_dateOfBirth);
  }

  /**
   * Sets the person's name (only if the string given is not empty)
   *
   * @param name - the person's new name
   */
  public void setName(String name) {
      if (name == null) {
          _name = "someone";
      } else {
          _name = name;
      }
  }

  /**
   * Sets the person's id (only if the id is 9 digits)
   *
   * @param id - the person's new id
   */
  public void setId(String id) {
      if (id.length() == MAX_ID) {
          _id = id;
      }
  }

  /**
   * Sets the person's date of birth
   *
   * @param d - the person's new date of birth
   */
  public void setDateOfBirth(Date d) {
      _dateOfBirth = new Date(d);
  }

  /**
   * Returns a String that represents this Person
   *
   * @return a String that represents this Person
   */
  public String toString() {
      return "Name: " + _name + "\n" +
      "ID: " + _id + "\n" +
      "Date of birth: " + _dateOfBirth.toString();
  }

  /**
   * Checks if two Person objects are the same
   *
   * @param other - another person object
   * @return true if all the attributes are the same
   */
  public boolean equals(Person other) {
      return this._name.equals(other._name) &&
      this._id.equals(other._id) &&
      this._dateOfBirth.equals(other._dateOfBirth);
  }

  /**
   * Checks which person is older
   *
   * @param other - another person object
   * @return 1 if this person is older than the other person , -1 if the other person is older than this person. If both people have the same birth date, return 0.
   */
  public int compareTo(Person other) {
      if (this._dateOfBirth.after(other._dateOfBirth)) {
          return -1;
      } else if (this._dateOfBirth.before(other._dateOfBirth)) {
          return 1;
      } else {
          return 0;
      }
  }
}
