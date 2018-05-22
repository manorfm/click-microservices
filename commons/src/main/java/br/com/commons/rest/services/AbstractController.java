package br.com.commons.rest.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractController<T> {

    public ResponseEntity<T> responseOk() {
        return response(HttpStatus.OK);
    }
    
    public ResponseEntity<T> responseCreated() {
    	return response(HttpStatus.CREATED);
    }
    
    public ResponseEntity<T> responseCreated(T t) {
    	return response(t, HttpStatus.CREATED);
    }
    
    public ResponseEntity<T> responseOk(T t) {
        return response(t, HttpStatus.OK);
    }

    public ResponseEntity<List<T>> responseOk(List<T> t) {
    	return response(t, HttpStatus.OK);
    }
    
    public ResponseEntity<T> responseError(HttpStatus status) {
    	return response(status);
    }

    public ResponseEntity<T> responseError(T t,HttpStatus status) {
    	return response(t, status);
    }
    
    private ResponseEntity<T> response(HttpStatus status) {
    	return new ResponseEntity<>(status);
    }
    
    public ResponseEntity<T> response(T t, HttpStatus status) {
    	return new ResponseEntity<>(t, status);
    }

    public ResponseEntity<List<T>> response(List<T> t, HttpStatus status) {
    	return new ResponseEntity<>(t, status);
    }
    
    protected String toString(Object o) {
    	if (o == null) {
    		return null;
    	}
    	
    	return String.valueOf(o);
    }
}
