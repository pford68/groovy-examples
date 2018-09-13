//def inject = "Hi!"  //Accessing this in a trait would throuw an error.  Traits are not closures.

/**
 * Simple interface with one method to
 * transform a String value.
 */
interface Transformer {
    String transform(String input)
}
 
/** Default trait will return the input value unchanged. */
trait DefaultTransformer implements Transformer {
   // def inject = "Hi!"   // This allows other traits in the channel to reference this.inject.
    String transform(String input) {
        input
    }
}
 
/** Transform the String value to upper case */
trait Upper implements Transformer {
    String transform(String input) {
        super.transform("$inject, ${input.toUpperCase()}")
    }
}
 
/** Remove 'mr' from input String value. */
trait Filter implements Transformer {
    String transform(String input) {
        super.transform(input - 'mr')
    }
}

 
/**
 * Simple class uses three traits. The value property get method
 * returns the transformed value.
 */
class StringTransformer implements DefaultTransformer, Upper, Filter {
    String value
    def inject = "Hi!"
    String getValue() { transform(value) }
}
 
// Create StringTransformer instance.
def transformer = new StringTransformer(value: 'mrhaki')
 
println transformer.value