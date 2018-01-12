package org.scope.jackson.mapper;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

@SuppressWarnings("rawtypes")
public class CapitalizedStrategy extends PropertyNamingStrategy {

	private static final long serialVersionUID = -1738048617444676437L;

		@Override
		  public String nameForField(MapperConfig config,
		   AnnotatedField field, String defaultName) {
		     return convert(defaultName);
		   
		  }
		  @Override
		  public String nameForGetterMethod(MapperConfig config,
		   AnnotatedMethod method, String defaultName) {
		     return convert(defaultName);
		  }
		  
		  @Override
		  public String nameForSetterMethod(MapperConfig config,
		    AnnotatedMethod method, String defaultName) {
		   String a = convert(defaultName); 
		   return a;
		  }
		  
		  public String convert(String defaultName )
		  {
		   char[] arr = defaultName.toCharArray();
		   if(arr.length !=0)
		   {
		    if ( Character.isLowerCase(arr[0])){
		     char upper = Character.toUpperCase(arr[0]);
		     arr[0] = upper;
		    }
		   }
		   return new StringBuilder().append(arr).toString();
		  }
}
