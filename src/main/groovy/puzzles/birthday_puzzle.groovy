package puzzles

import java.time.Month
import static java.time.Month.*

/**
 * Created by lnunno on 4/19/15.
 */
class Birthdate {
    Month month
    int day


    @Override
    public String toString() {
        return month.toString() + ' ' + day;
    }
}

def isMonthUnique(Birthdate bday, List<Birthdate> birthdateList) {
    return birthdateList.count { it.month == bday.month } == 1
}

def isDayUnique(Birthdate bday, List<Birthdate> birthdateList) {
    return birthdateList.count { it.day == bday.day } == 1
}

pbds = [
        new Birthdate(month: MAY, day: 15),
        new Birthdate(month: MAY, day: 16),
        new Birthdate(month: MAY, day: 19),
        new Birthdate(month: JUNE, day: 17),
        new Birthdate(month: JUNE, day: 18),
        new Birthdate(month: JULY, day: 14),
        new Birthdate(month: JULY, day: 16),
        new Birthdate(month: AUGUST, day: 14),
        new Birthdate(month: AUGUST, day: 15),
        new Birthdate(month: AUGUST, day: 17),
]
println 'Possible birthdays step 1: ' + pbds
def um = pbds.findAll {
    !isMonthUnique(it, pbds)
}

def ud = pbds.findAll{
    !isDayUnique(it, pbds)
}

println 'Non-unique months: ' + um + ' LEN=' + um.size()
println 'Non-unique days: ' + ud + ' LEN=' + ud.size()
def s2 = ud.findAll { isDayUnique(it, ud)}
println 'Step 2: ' + s2
