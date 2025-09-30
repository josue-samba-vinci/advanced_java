package domaine;

import java.time.Duration;

public class Instruction {

    private String description;
    private Duration dureeEnMinutes;
    public Instruction(String description, int dureeEnMinutes){
        this.description = description;
        this.dureeEnMinutes = Duration.ofMinutes(dureeEnMinutes);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public void setDureeEnMinutes(Duration dureeEnMinutes) {
       if (!dureeEnMinutes.equals(Duration.ofMinutes(dureeEnMinutes.toMinutes()))) throw new IllegalArgumentException();
        this.dureeEnMinutes = dureeEnMinutes;
    }

    @Override
    public String toString() {
        //long hours = dureeEnMinutes.toHours();
       // long minutes = dureeEnMinutes.toMinutes();
        //return hours +" "+ minutes +" est la duree de "+ description;
        return "(" + String.format("%02d:%02d",dureeEnMinutes.toHours(),dureeEnMinutes.toMinutesPart()) + ") " + description ;
    }
}
