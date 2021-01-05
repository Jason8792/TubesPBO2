package Model;

public class Leaderboard {
    private String tanggal;
    private String status;
    private String name;
    public Leaderboard(String name , String tanggal , String status){
        this.name = name;
        this.tanggal = tanggal;
        this.status = status;
    }
    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
