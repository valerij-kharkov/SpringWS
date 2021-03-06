
package ua.com.csltd.beans;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ua.com.csltd.beans package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ua.com.csltd.beans
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddPersonRequest }
     * 
     */
    public AddPersonRequest createAddPersonRequest() {
        return new AddPersonRequest();
    }

    /**
     * Create an instance of {@link PersonRequest }
     * 
     */
    public PersonRequest createPersonRequest() {
        return new PersonRequest();
    }

    /**
     * Create an instance of {@link ChangePersonRequest }
     * 
     */
    public ChangePersonRequest createChangePersonRequest() {
        return new ChangePersonRequest();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link AddPersonResponse }
     * 
     */
    public AddPersonResponse createAddPersonResponse() {
        return new AddPersonResponse();
    }

    /**
     * Create an instance of {@link PersonResponse }
     * 
     */
    public PersonResponse createPersonResponse() {
        return new PersonResponse();
    }

    /**
     * Create an instance of {@link ChangePersonResponse }
     * 
     */
    public ChangePersonResponse createChangePersonResponse() {
        return new ChangePersonResponse();
    }

}
