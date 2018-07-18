package fix;

import common.InstanceFactory;
import common.Utility;
import quickfix.Field;
import quickfix.FieldMap;
import quickfix.UtcTimeStampField;
import quickfix.field.converter.BooleanConverter;
import quickfix.field.converter.CharConverter;
import quickfix.field.converter.DoubleConverter;
import quickfix.field.converter.IntConverter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew Demetriou on 17/07/2018.
 */
public class CucumberFixParser extends InstanceFactory {

    private Utility utils = new Utility();

    public <T extends FieldMap> List<T> createFixSet(List<List<String>> dataTable, Class<T> subType) {
        List<T> fixSet = new ArrayList<>();
        List<String> headers = dataTable.get( 0 );
        List<List<String>> rows = dataTable.subList( 1, (dataTable.size()) );

        for ( List<String> row : rows ) {
            fixSet.add( createFix( headers, row, subType ) );
        }

        return fixSet;
    }

    public <T extends FieldMap> T createFix(List<String> headers, List<String> row, Class<T> type) {
        T fix = getInstanceOfGeneric( type );
        for (int i = 0; i < headers.size(); i++) {
            Field field;
            if (utils.isInteger( headers.get( i ) )) {
                int tag = Integer.parseInt( headers.get( i ) );
                field = new Field( tag, row.get( i ) );
                fix.setField( tag, field );
                continue;
            }

            Class<? extends Field> fieldClass = (Class<? extends Field>) getClassNameFromString( "quickfix.field." + headers.get( i ) );
            Constructor<?> constructor = fieldClass.getConstructors()[1];
            Parameter parameter = constructor.getParameters()[0];

            try {
                int tag = getInstanceOfGeneric( fieldClass ).getTag();
                String value = row.get(i);
                if (parameter.getType().getName().equals( "java.time.LocalDateTime" )) {
                    UtcTimeStampField timeField = new UtcTimeStampField( tag );
                    if (value.isEmpty()) {
                        timeField.setValue( LocalDateTime.now() );
                    } else {
                        timeField.setValue( LocalDateTime.parse(value));
                    }
                    fix.setField( timeField );
                } else {
                    field = new Field( tag, ConvertFieldType( parameter.getType(), value ) );
                    fix.setField(tag, field);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fix;
    }

    private <T> T ConvertFieldType(Class<T> type, String value) throws Exception {
        String typeName = type.getName();
        Object result = null;

        if (typeName.equals( "java.lang.String" )) {
            result = value;
        } else if (typeName.equals( "int" )) {
            result = IntConverter.convert( value );
        } else if (typeName.equals( "double" )) {
            result = DoubleConverter.convert( value );
        } else if (typeName.equals( "char" )) {
            result = CharConverter.convert( value );
        } else if (typeName.equals( "boolean" )) {
            result = BooleanConverter.convert( value );
        } else {
            throw new Exception( "Cannot parse type: " + type.getName());
        }

        if (result == null) {
            throw new NullPointerException( "Object 'result' was null." );
        }
        return (T) result;
    }

}
