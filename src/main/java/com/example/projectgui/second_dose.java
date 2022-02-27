package com.example.projectgui;

import java.sql.SQLException;

public interface second_dose {
    String schedule_seconddose(String first_dose_date) throws SQLException;

    /*how schedule_seconddose works

    the second appointment will take place 3 weeks after the first appointment (so 21 days)

    appointment_date = "2021-01-26"

    1.separate day,month,year from 'appointment_date'
        1.1 by .split() method, this results in ["2021","01","26"]
        1.2 create a new "integer array" then use for loop to add ["2021","01","26"] into the new array
            1.2.1 we have to cast a string date to integer first
    2. see which month did the user choose by using "switch(month){}"
        2.1 there should be 3 cases, months with 31 days, months with 30 days, and February with 28 days
        2.2 for each case, see if the new appointment date is out of bound(for example, 2021-12-32)
            2.2.1 if yes, recalculate the new appointment date, and increment the month, or year
            2.2.2 if no, clear the "integer array", then input the new data into it
    3. convert the integer array back into string (because we set the data type for appointment date to be string)
        3.1 by creating a string variable then use .get() method from the "integer array" and add with '/'
    4. return that String value :3


     */
}
