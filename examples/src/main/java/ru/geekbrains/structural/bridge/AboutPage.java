package ru.geekbrains.structural.bridge;

public class AboutPage extends WebPage {

    private final Theme theme;

    public AboutPage(Theme theme) {
        this.theme = theme;
    }

    @Override
    public String getContent() {
        return "Страница с информацией" + theme.getTheme();
    }
}
