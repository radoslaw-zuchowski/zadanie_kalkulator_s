package com.zuchol.converter.service.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.zuchol.converter.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
    private MessageSource messageSource;
	
	@Override
	public String getMessage(String message) {
		return messageSource.getMessage(message, null, Locale.ENGLISH);
	}

}
