public class Stats {
    private int rank;
    private int number;
    private int year;
    
    public Stats(int rank, int number, int year) {
	this.rank = rank;
	this.number = number;
	this.year = year;
    }

    public Stats(int number, int year) {
	this.number = number;
	this.year = year;
    }
    
    public int getRank() {
	return rank;
    }

    public int getNumber() {
	return number;
    }

    public int getYear() {
	return year;
    }
    public void setRank(int rank) {
	this.rank = rank;
    }

    public void setNumber(int number) {
	this.number = number;
    }

    public void setYear(int year) {
	this.year = year;
    }

    public String toString() {
	return rank + ", " + number + ", ";
    }
}
