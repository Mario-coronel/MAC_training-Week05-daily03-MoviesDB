package com.example.admin.moviesdb.main;

public interface MainContract {


    interface View {

        void showResults();
        void showError();
        void showMovie();


    }

    interface Presenter {

        void getResults();

    }


}
