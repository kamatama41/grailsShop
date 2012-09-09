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
        isbn13(matches:"\\p{Digit}{13}")
        imageUrl(nullable : true)
    }
}
