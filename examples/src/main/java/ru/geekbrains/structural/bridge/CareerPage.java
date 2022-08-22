package ru.geekbrains.structural.bridge;

public class CareerPage extends WebPage {

    private final Theme theme;

    public CareerPage(Theme theme) {
        this.theme = theme;
    }


    @Override
    public String getContent() {
        return "Страница карьеры" + theme.getTheme();
    }
}
