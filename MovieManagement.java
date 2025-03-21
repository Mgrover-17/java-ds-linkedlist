public class MovieManagement {
    static class Movie {
        double  rating;
        String movieTitle;
        String director;
        int yearOfRelease;
        Movie next;
        Movie prev;

        Movie(String movieTitle, String director, int yearOfRelease) {
            this.movieTitle = movieTitle;
            this.director = director;
            this.yearOfRelease = yearOfRelease;
            this.next = null;
            this.prev = null;
        }
    }

    static class MovieDetails {
        private Movie head;
        private Movie tail;

        public void addMovieFirst(String movieTitle, String director, int yearOfRelease) {
            Movie newMovie = new Movie(movieTitle, director, yearOfRelease);
            if (head == null) {
                head = tail = newMovie;
            } else {
                head.prev = newMovie;
                newMovie.next = head;
                head = newMovie;
            }
        }

        public void addMovieLast(String movieTitle, String director, int yearOfRelease){
            Movie newMovie = new Movie(movieTitle, director, yearOfRelease);
            if (tail == null) {
                tail = head = newMovie;
            } else {
                tail.next=newMovie;
                newMovie.prev=tail;
                tail=newMovie;
            }
        }

        public void addSpecificPosition(String movieTitle, String director, int yearOfRelease, int position){
            Movie newMovie = new Movie(movieTitle, director, yearOfRelease);
              if(position<=1){
                  addMovieFirst(movieTitle, director, yearOfRelease);
              }
              Movie current=head;
              for(int i=1;current!=null && i<position-1;i++){
                  current=current.next;
              }
            if (current == null || current.next == null) {
                addMovieLast(movieTitle, director, yearOfRelease);
            } else {
                newMovie.next=current.next;
                newMovie.prev=current;
                if (current.next != null) {
                    current.next.prev = newMovie; // Update next node's prev pointer
                }
                current.next = newMovie;
            }
        }

        public void removeByTitle(String movieTitle) {
            Movie current = head;
            while (current != null) {
                if (current.movieTitle.equalsIgnoreCase(movieTitle)) {
                    if (current == head) {
                        head = head.next;
                        if (head != null) head.prev = null;
                    } else if (current == tail) {
                        tail = tail.prev;
                        tail.next = null;
                    } else {
                        current.prev.next = current.next;
                        current.next.prev = current.prev;
                    }
                    return;
                }
                current = current.next;
            }
        }

        public void searchByDirector(String director) {
            Movie current = head;
            while (current != null) {
                if (current.director.equalsIgnoreCase(director)) {
                    System.out.println(current.movieTitle + " - " + current.yearOfRelease + " - " + current.rating);
                }
                current = current.next;
            }
        }

        public void searchByRating(double rating) {
            Movie current = head;
            while (current != null) {
                if (current.rating == rating) {
                    System.out.println(current.movieTitle + " - " + current.director + " - " + current.yearOfRelease);
                }
                current = current.next;
            }
        }

        public void updateRating(String movieTitle, double newRating) {
            Movie current = head;
            while (current != null) {
                if (current.movieTitle.equalsIgnoreCase(movieTitle)) {
                    current.rating = newRating;
                    return;
                }
                current = current.next;
            }
        }

        public void displayForward(){
            Movie current=head;
            while(current!=null){
                System.out.println("movie title:"+current.movieTitle+"  movie director:"+current.director+"  movie year of release:"+ current.yearOfRelease);
                current=current.next;
            }
        }



        public void displayBackward(){
            Movie current=tail;
            while(current!=null){
                System.out.println("movie title:"+current.movieTitle+"  movie director:"+current.director+"  movie year of release:"+ current.yearOfRelease);
                current=current.prev;
            }
        }
    }


    public static void main(String[] args) {
        MovieDetails list=new MovieDetails();
        list.addMovieFirst("Inception", "Christopher Nolan", 2010);
        list.addMovieLast("The Matrix", "Lana Wachowski", 1999);
        list.addSpecificPosition("Avatar", "James Cameron", 2009,  2);
        list.searchByDirector("Christopher Nolan");

        list.displayForward();
        System.out.println("\nUpdating rating for 'Inception':");
        list.updateRating("Inception", 9.0);
        list.displayForward();
        System.out.println("\nRemoving 'The Matrix':");
        list.removeByTitle("The Matrix");
        list.displayForward();
//        list.displayBackward();
    }
}


