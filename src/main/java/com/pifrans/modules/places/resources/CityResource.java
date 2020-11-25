package com.pifrans.modules.places.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pifrans.global.resources.GenericResource;
import com.pifrans.modules.places.models.City;

@RestController
@RequestMapping(value = "/cities")
public class CityResource extends GenericResource<City> {
	//private static final Logger LOG = Logger.getLogger(CityResource.class.getName());

}
