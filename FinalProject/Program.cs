namespace FinalProject
{
  internal class Program
  {
    private static void ShowPerson(string category)
    {
      try
      {
        SqlConnection mySqlConnection = new SqlConnection("server=LocalHost\\SQLEXPRESS;database=dugma;Integrated Security=SSPI;");
        SqlCommand mySqlCommand = mySqlConnection.CreateCommand();
        mySqlCommand.CommandText = $"Select * from {category}"; // command SQL
        mySqlConnection.Open();
        SqlDataReader mySqlDataReader = mySqlCommand.ExecuteReader();
        Console.WriteLine("Id\tName\tCity\tOther Info");

        while (mySqlDataReader.Read())
        {
          if (category == "student")
          {
            Console.WriteLine("{0}\t{1}\t{2}\t{3}", mySqlDataReader["id"].ToString(), mySqlDataReader["fname"].ToString(), mySqlDataReader["city"].ToString(), mySqlDataReader["grade"].ToString());
          }
          else if (category == "instructor")
          {
            Console.WriteLine("{0}\t{1}\t{2}\t{3}, {4}", mySqlDataReader["id"].ToString(), mySqlDataReader["name"].ToString(), mySqlDataReader["city"].ToString(), mySqlDataReader["salary"].ToString(), mySqlDataReader["course"].ToString());
          }
          else if (category == "course_coordinator")
          {
            Console.WriteLine("{0}\t{1}\t{2}\t{3}, {4}", mySqlDataReader["id"].ToString(), mySqlDataReader["name"].ToString(), mySqlDataReader["city"].ToString(), mySqlDataReader["salary"].ToString(), mySqlDataReader["department"].ToString());
          }
        }

        mySqlDataReader.Close();
        mySqlConnection.Close();
      }
      catch (Exception err)
      {
        Console.WriteLine(err.Message);
      }
    }

    private static void EnterPerson(string category)
    {
      try
      {
        SqlConnection mySqlConnection = new SqlConnection("server=LocalHost\\SQLEXPRESS;database=dugma;Integrated Security=SSPI;");
        SqlCommand mySqlCommand = mySqlConnection.CreateCommand();

        Console.WriteLine("Enter ID:");
        string id = Console.ReadLine();
        Console.WriteLine("Enter name:");
        string name = Console.ReadLine();
        Console.WriteLine("Enter city:");
        string city = Console.ReadLine();

        if (category == "student")
        {
          Console.WriteLine("Enter grade:");
          int grade = int.Parse(Console.ReadLine());
          mySqlCommand.CommandText = $"insert into student values('{id}', '{name}', {grade}, '{city}')";
        }
        else if (category == "instructor")
        {
          Console.WriteLine("Enter salary:");
          decimal salary = decimal.Parse(Console.ReadLine());
          Console.WriteLine("Enter course:");
          string course = Console.ReadLine();
          mySqlCommand.CommandText = $"insert into instructor values('{id}', '{name}', '{city}', {salary}, '{course}')";
        }
        else if (category == "course_coordinator")
        {
          Console.WriteLine("Enter salary:");
          decimal salary = decimal.Parse(Console.ReadLine());
          Console.WriteLine("Enter department:");
          string department = Console.ReadLine();
          mySqlCommand.CommandText = $"insert into course_coordinator values('{id}', '{name}', '{city}', {salary}, '{department}')";
        }

        mySqlConnection.Open();
        mySqlCommand.ExecuteNonQuery();
        mySqlConnection.Close();
        Console.WriteLine($"{category} added successfully!");
      }
      catch (Exception err)
      {
        Console.WriteLine(err.Message);
      }
    }

    private static void DeletePerson(string category, string name)
    {
      try
      {
        SqlConnection mySqlConnection = new SqlConnection("server=LocalHost\\SQLEXPRESS;database=dugma;Integrated Security=SSPI;");
        SqlCommand mySqlCommand = mySqlConnection.CreateCommand();
        mySqlCommand.CommandText = $"DELETE FROM {category} WHERE name = @name";
        mySqlCommand.Parameters.AddWithValue("@name", name);

        mySqlConnection.Open();
        int rowsAffected = mySqlCommand.ExecuteNonQuery();
        mySqlConnection.Close();

        if (rowsAffected > 0)
        {
          Console.WriteLine($"{name} has been deleted from {category}.");
        }
        else
        {
          Console.WriteLine($"{name} was not found in {category}.");
        }
      }
      catch (Exception err)
      {
        Console.WriteLine(err.Message);
      }
    }

    private static void CalculateAverageSalary(string category)
    {
      try
      {
        SqlConnection mySqlConnection = new SqlConnection("server=LocalHost\\SQLEXPRESS;database=dugma;Integrated Security=SSPI;");
        SqlCommand mySqlCommand = mySqlConnection.CreateCommand();
        mySqlCommand.CommandText = category switch
        {
          "instructor" => "SELECT AVG(salary) FROM instructor",
          "course_coordinator" => "SELECT AVG(salary) FROM course_coordinator",
          _ => throw new Exception("Invalid category choice")
        };

        mySqlConnection.Open();
        object result = mySqlCommand.ExecuteScalar();
        mySqlConnection.Close();

        if (result != DBNull.Value)
        {
          decimal averageSalary = Convert.ToDecimal(result);
          Console.WriteLine($"The average salary for {category} is {averageSalary:C2}.");
        }
        else
        {
          Console.WriteLine($"No salary data available for {category}.");
        }
      }
      catch (Exception err)
      {
        Console.WriteLine(err.Message);
      }
    }

    private static void ShowPeopleByCity(string city)
    {
      try
      {
        SqlConnection mySqlConnection = new SqlConnection("server=LocalHost\\SQLEXPRESS;database=dugma;Integrated Security=SSPI;");

        // Show students
        SqlCommand mySqlCommand = mySqlConnection.CreateCommand();
        mySqlCommand.CommandText = "SELECT 'Student' AS Category, id, fname AS Name, city, grade AS OtherInfo FROM student WHERE city = @city";
        mySqlCommand.Parameters.AddWithValue("@city", city);
        mySqlConnection.Open();
        SqlDataReader mySqlDataReader = mySqlCommand.ExecuteReader();
        Console.WriteLine("Category\tId\tName\tCity\tOther Info");
        while (mySqlDataReader.Read())
        {
          Console.WriteLine("{0}\t{1}\t{2}\t{3}\t{4}", mySqlDataReader["Category"].ToString(), mySqlDataReader["id"].ToString(), mySqlDataReader["Name"].ToString(), mySqlDataReader["city"].ToString(), mySqlDataReader["OtherInfo"].ToString());
        }
        mySqlDataReader.Close();

        // Show instructors
        mySqlCommand.CommandText = "SELECT 'Instructor' AS Category, id, name AS Name, city, salary AS OtherInfo FROM instructor WHERE city = @city";
        mySqlDataReader = mySqlCommand.ExecuteReader();
        while (mySqlDataReader.Read())
        {
          Console.WriteLine("{0}\t{1}\t{2}\t{3}\t{4}", mySqlDataReader["Category"].ToString(), mySqlDataReader["id"].ToString(), mySqlDataReader["Name"].ToString(), mySqlDataReader["city"].ToString(), mySqlDataReader["OtherInfo"].ToString());
        }
        mySqlDataReader.Close();

        // Show course coordinators
        mySqlCommand.CommandText = "SELECT 'Course Coordinator' AS Category, id, name AS Name, city, salary AS OtherInfo FROM course_coordinator WHERE city = @city";
        mySqlDataReader = mySqlCommand.ExecuteReader();
        while (mySqlDataReader.Read())
        {
          Console.WriteLine("{0}\t{1}\t{2}\t{3}\t{4}", mySqlDataReader["Category"].ToString(), mySqlDataReader["id"].ToString(), mySqlDataReader["Name"].ToString(), mySqlDataReader["city"].ToString(), mySqlDataReader["OtherInfo"].ToString());
        }

        mySqlDataReader.Close();
        mySqlConnection.Close();
      }
      catch (Exception err)
      {
        Console.WriteLine(err.Message);
      }
    }

    private static void FuncNotSelect(string strSQL)
    {
      try
      {
        SqlConnection mySqlConnection = new SqlConnection("server=LocalHost\\SQLEXPRESS;database=dugma;Integrated Security=SSPI;");
        SqlCommand mySqlCommand = mySqlConnection.CreateCommand();
        mySqlCommand.CommandText = strSQL; // command SQL
        mySqlConnection.Open();
        int n = mySqlCommand.ExecuteNonQuery();
        Console.WriteLine("affected rows : " + n);
        mySqlConnection.Close();
      }
      catch (Exception err)
      {
        Console.WriteLine(err.Message);
      }
    }

    private static void UpdateStudentGrade()
    {
      try
      {
        SqlConnection mySqlConnection = new SqlConnection("server=LocalHost\\SQLEXPRESS;database=dugma;Integrated Security=SSPI;");
        SqlCommand mySqlCommand = mySqlConnection.CreateCommand();

        Console.WriteLine("Enter ID of student:");
        string idUpdate = Console.ReadLine();
        Console.WriteLine("Enter new grade:");
        int newGrade = int.Parse(Console.ReadLine());

        mySqlCommand.CommandText = $"update student set grade = {newGrade} where id = '{idUpdate}';";
        mySqlConnection.Open();
        mySqlCommand.ExecuteNonQuery();
        mySqlConnection.Close();
        Console.WriteLine("Student grade updated successfully!");
      }
      catch (Exception err)
      {
        Console.WriteLine(err.Message);
      }
    }

    private static void BestStudent()

    {
      try
      {
        SqlConnection mySqlConnection = new SqlConnection("server=LocalHost\\SQLEXPRESS;database=dugma;Integrated Security=SSPI;");
        SqlCommand mySqlCommand = mySqlConnection.CreateCommand();
        mySqlCommand.CommandText = "select fname,grade from student where grade=(select max(grade) from student);"; // command SQL
        mySqlConnection.Open();
        SqlDataReader mySqlDataReader = mySqlCommand.ExecuteReader();
        Console.WriteLine("Name\tGrade");
        while (mySqlDataReader.Read())
        {

          Console.WriteLine("{0}\t{1}", mySqlDataReader[0].ToString(), mySqlDataReader[1].ToString());
        }

        mySqlDataReader.Close();
        mySqlConnection.Close();
      }
      catch (Exception err)
      {
        Console.WriteLine(err.Message);
      }
    }

    private static void ShowDetailedPeopleInCity()
    {
      try
      {
        SqlConnection mySqlConnection = new SqlConnection("server=LocalHost\\SQLEXPRESS;database=dugma;Integrated Security=SSPI;");
        SqlCommand mySqlCommand = mySqlConnection.CreateCommand();

        Console.WriteLine("Enter city:");
        string city = Console.ReadLine();

        mySqlCommand.CommandText = @"
            SELECT 'Student' AS Category, s.id, s.fname AS Name, s.city, CAST(s.grade AS VARCHAR) AS AdditionalInfo
            FROM student s
            WHERE s.city = @city
            UNION ALL
            SELECT 'Instructor' AS Category, i.id, i.name AS Name, i.city, CAST(i.salary AS VARCHAR) AS AdditionalInfo
            FROM instructor i
            WHERE i.city = @city
            UNION ALL
            SELECT 'Course Coordinator' AS Category, c.id, c.name AS Name, c.city, CAST(c.salary AS VARCHAR) AS AdditionalInfo
            FROM course_coordinator c
            WHERE c.city = @city;
        ";

        mySqlCommand.Parameters.AddWithValue("@city", city);

        mySqlConnection.Open();
        SqlDataReader mySqlDataReader = mySqlCommand.ExecuteReader();

        Console.WriteLine("Category\tId\tName\tCity\tAdditional Info");
        while (mySqlDataReader.Read())
        {
          Console.WriteLine("{0}\t{1}\t{2}\t{3}\t{4}", mySqlDataReader["Category"], mySqlDataReader["id"], mySqlDataReader["Name"], mySqlDataReader["city"], mySqlDataReader["AdditionalInfo"]);
        }

        mySqlDataReader.Close();
        mySqlConnection.Close();
      }
      catch (Exception err)
      {
        Console.WriteLine(err.Message);
      }
    }


    static void Main(string[] args)
    {
      // Create the students table
      FuncNotSelect("create table student (id nvarchar(9) not null primary key,fname nvarchar(10),grade int check (grade >=0 and grade<=100), city nvarchar(10))");
      FuncNotSelect("insert into student values('111','Alisa', 100, 'Paris'),('222','Laila', 100, 'London'),('333','Lior',100,'Rome'),('444','Tom',30,'Paris')");

      // Create the instructor table
      FuncNotSelect("create table instructor (id nvarchar(9) not null primary key, name nvarchar(50), city nvarchar(10), salary decimal(10,2), course nvarchar(50))");

      // Inserting sample data for instructors
      FuncNotSelect("insert into instructor values('101', 'Dr. Smith', 'Tel Aviv', 15000.00, 'Mathematics')");
      FuncNotSelect("insert into instructor values('102', 'Ms. Cohen', 'Haifa', 12000.00, 'Physics')");
      FuncNotSelect("insert into instructor values('103', 'Mr. Levi', 'Jerusalem', 17000.00, 'Computer Science')");
      FuncNotSelect("insert into instructor values('104', 'Dr. Katz', 'Beer Sheva', 16000.00, 'History')");
      FuncNotSelect("insert into instructor values('105', 'Ms. Ben-Ami', 'Netanya', 13000.00, 'Biology')");
      FuncNotSelect("insert into instructor values('105', 'Ms. Ben-Ami', 'Paris', 13000.00, 'Biology')");

      // Create the course coordinators table
      FuncNotSelect("create table course_coordinator (id nvarchar(9) not null primary key, name nvarchar(50), city nvarchar(10), salary decimal(10,2), department nvarchar(50))");

      // Inserting sample data for course coordinators
      FuncNotSelect("insert into course_coordinator values('201', 'Prof. Green', 'Tel Aviv', 20000.00, 'Engineering')");
      FuncNotSelect("insert into course_coordinator values('202', 'Dr. Brown', 'Haifa', 18000.00, 'Science')");
      FuncNotSelect("insert into course_coordinator values('203', 'Prof. White', 'Jerusalem', 22000.00, 'Medicine')");
      FuncNotSelect("insert into course_coordinator values('204', 'Dr. Black', 'Beer Sheva', 19000.00, 'Law')");
      FuncNotSelect("insert into course_coordinator values('205', 'Prof. Gray', 'Netanya', 21000.00, 'Business')");
      FuncNotSelect("insert into course_coordinator values('205', 'Prof. Gray', 'Paris', 21000.00, 'Business')");

      while (true)
      {

        Console.Clear();
        Console.WriteLine("1. Show All People");
        Console.WriteLine("2. Enter New Person");
        Console.WriteLine("3. Delete Person");
        Console.WriteLine("4. Average Salary");
        Console.WriteLine("5. Show People by City");
        Console.WriteLine("6. Update Grade");
        Console.WriteLine("7. Best Student");
        Console.WriteLine("8. Detailed People in City"); //with join func
        Console.WriteLine("9. Exit");
        Console.WriteLine("Enter number: ");
        int num = int.Parse(Console.ReadLine());


        switch (num)
        {
          case 1:
            Console.WriteLine("Choose category:");
            Console.WriteLine("1. Student");
            Console.WriteLine("2. Instructor");
            Console.WriteLine("3. Course Coordinator");
            int categoryChoice = int.Parse(Console.ReadLine());
            string category = categoryChoice switch
            {
              1 => "student",
              2 => "instructor",
              3 => "course_coordinator",
              _ => throw new Exception("Invalid category choice")
            };
            ShowPerson(category);
            Console.ReadKey();
            break;
          case 2:
            Console.WriteLine("Choose category to enter new person:");
            Console.WriteLine("1. Student");
            Console.WriteLine("2. Instructor");
            Console.WriteLine("3. Course Coordinator");
            int enterCategoryChoice = int.Parse(Console.ReadLine());
            string enterCategory = enterCategoryChoice switch
            {
              1 => "student",
              2 => "instructor",
              3 => "course_coordinator",
              _ => throw new Exception("Invalid category choice")
            };
            EnterPerson(enterCategory);
            Console.ReadKey();
            break;
          case 3:
            Console.WriteLine("Choose category to delete person:");
            Console.WriteLine("1. Student");
            Console.WriteLine("2. Instructor");
            Console.WriteLine("3. Course Coordinator");
            int deleteCategoryChoice = int.Parse(Console.ReadLine());
            string deleteCategory = deleteCategoryChoice switch
            {
              1 => "student",
              2 => "instructor",
              3 => "course_coordinator",
              _ => throw new Exception("Invalid category choice")
            };

            Console.WriteLine("Enter name of person to delete:");
            string nameToDelete = Console.ReadLine();

            DeletePerson(deleteCategory, nameToDelete);
            Console.ReadKey();
            break;

          case 4:
            Console.WriteLine("Choose category to calculate average salary:");
            Console.WriteLine("1. Instructor");
            Console.WriteLine("2. Course Coordinator");
            int avgSalaryCategoryChoice = int.Parse(Console.ReadLine());
            string avgSalaryCategory = avgSalaryCategoryChoice switch
            {
              1 => "instructor",
              2 => "course_coordinator",
              _ => throw new Exception("Invalid category choice")
            };

            CalculateAverageSalary(avgSalaryCategory);
            Console.ReadKey();
            break;

          case 5:
            Console.WriteLine("Enter city:");
            string city = Console.ReadLine();
            ShowPeopleByCity(city);
            Console.ReadKey();
            break;

          case 6:
            UpdateStudentGrade();
            Console.ReadLine();
            break;

          case 7:
            BestStudent();
            Console.ReadLine();
            break;
          case 8:
            ShowDetailedPeopleInCity();
            Console.ReadLine();
            break;
          case 9:
            Console.WriteLine("Good Bye !!! ");
            Console.ReadKey();
            return;
          default:
            Console.WriteLine("Invalid option, please try again.");
            Console.ReadKey();
            break;
        }
      }
    }
  }
}
