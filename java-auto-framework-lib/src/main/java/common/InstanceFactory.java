package common;

/**
 * Created by Andrew Demetriou on 17/07/2018.
 */
public class InstanceFactory {

    public <T> T getInstanceOfGeneric(Class<? extends T> genericClass){
        T type = null;
        try {
            type = genericClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return type;
    }

    public Class<?> getClassNameFromString(String fullQualifiedName) {
        Class<?> type = null;
        try {
            type = Class.forName(fullQualifiedName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return type;
    }
}
