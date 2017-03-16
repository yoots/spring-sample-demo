/**
 * 
 */
package com.spring.demo.http;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.spring.demo.common.Const;
import com.spring.demo.util.Utils;

/**
 * @author user
 *
 */
public class EntityMapArgumentResolver implements HandlerMethodArgumentResolver {

	public EntityMapArgumentResolver() {
		System.out.println(" ================== EntityMap ======================== ");
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		if (parameter.getParameterType().equals(Map.class)) {

			Map<String, Object> params = new HashMap<String, Object>();

			Iterator<String> iterator = webRequest.getParameterNames();

			while (iterator.hasNext()) {
				String key = iterator.next();
				String[] values = webRequest.getParameterValues(key);
				if (Const.excludeKey.contains(key)) {
					if (values != null)
						params.put(key, (values.length > 1) ? values : values[0]);
				} else {
					if (values != null)
						params.put(key, (values.length > 1) ? values : Utils.tagRemove(values[0]));
				}

			}
			return params;
		}
		return null;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {

		return true;
	}
}
