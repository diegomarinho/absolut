package br.absolut.apresentacao.seguranca;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

public class SegurancaAdvice implements MethodBeforeAdvice,
		AfterReturningAdvice, ThrowsAdvice {

	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		// TODO Auto-generated method stub
		
	}

	public void afterReturning(Object arg0, Method arg1, Object[] arg2,
			Object arg3) throws Throwable {
		// TODO Auto-generated method stub
		
	}

}
