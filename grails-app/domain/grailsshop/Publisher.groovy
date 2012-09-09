package grailsshop

class Publisher {
    static hasMany = [books : Book]

    String name

    static constraints = {
        name(blank:false)
    }

    String toString() {
        name
    }
}
