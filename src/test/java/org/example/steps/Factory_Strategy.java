package org.example.steps;

import org.testng.annotations.Test;

public class Factory_Strategy {

    public interface Search {
        void doSearch(String keyWord);
    }

    public class NameSearch implements Search {
        public void doSearch(String keyWord) {
            System.out.println("Name searching: " + keyWord);
        }
    }

    public class EmailSearch implements Search {
        public void doSearch(String keyWord) {
            System.out.println("Email searching: " + keyWord);
        }
    }

    public class SearchPage {
        private Search strategy;

        public void setStrategy(Search strategy) { this.strategy = strategy; }
        public void performSearch(String query) { strategy.doSearch(query); }
    }

    public class SearchFactory {
        public Search createSearch(String type) {
            switch (type.toLowerCase()) {
                case "name":
                    return new NameSearch();
                case "email":
                    return new EmailSearch();
                default:
                    throw new IllegalArgumentException("Unknown search type: " + type);
            }
        }
    }

    @Test
    public void strategyTest() {
        SearchPage searchPage = new SearchPage();
        searchPage.setStrategy(new NameSearch());
        searchPage.performSearch("Harish");

        searchPage.setStrategy(new EmailSearch());
        searchPage.performSearch("harish.avenue1@gmail.com");
    }

    @Test
    public void factoryTest() {
        Search nameSearch = new SearchFactory().createSearch("name");
        nameSearch.doSearch("Harish");

        Search emailSearch = new SearchFactory().createSearch("email");
        emailSearch.doSearch("harish.avenue1@gmail.com");
    }
}
