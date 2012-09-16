package grailsshop

class Book {

    static belongsTo = [ publisher : Publisher ]

    String title
    String author
    int price
    Date releaseDate
    String isbn13
    String imageUrl

    static constraints = {
        title(blank:false)
        author(blank:false)
        price(minSize:0)
        releaseDate()
        isbn13(matches:"[0-9]{13}")
        imageUrl(nullable : true)
    }

    String toString() {
        title
    }
}
