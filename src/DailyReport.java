import java.util.Date;

public class DailyReport {

    private double dailySale;
    private Date date;

    public DailyReport(double dailySale, Date date){
        this.dailySale = dailySale;
        this.date = date;
    }

    public double getDailySale() {
        return dailySale;
    }

    public Date getDate() {
        return date;
    }
}
