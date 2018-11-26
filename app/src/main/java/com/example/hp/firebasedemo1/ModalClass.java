package com.example.hp.firebasedemo1;

class ModalClass
{
    String name;
    String specialist;
    int image;

    public ModalClass(String name, String specialist, int image)
    {
        this.name = name;
        this.specialist = specialist;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

