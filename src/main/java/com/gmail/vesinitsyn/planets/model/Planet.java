package com.gmail.vesinitsyn.planets.model;

import javax.persistence.*;

@Entity
@Table(name = "planets")
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "planets_id_seq")
    @SequenceGenerator(name = "planets_id_seq", sequenceName = "planets_id_seq", allocationSize = 1)
    private long id;
    private String name;
    private float radius;
    private float orbit;
    private float speed;
    private String color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Override
    public String toString() {
        return "Planet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", radius=" + radius +
                ", orbit=" + orbit +
                ", speed=" + speed +
                ", color='" + color + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getOrbit() {
        return orbit;
    }

    public void setOrbit(float orbit) {
        this.orbit = orbit;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
