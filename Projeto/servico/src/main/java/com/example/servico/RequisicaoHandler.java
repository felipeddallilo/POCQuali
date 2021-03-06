package com.example.servico;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

@Component
@ExternalTaskSubscription("login")
public class RequisicaoHandler implements ExternalTaskHandler {

	@Override
	public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {	

		String resultado = "INVALIDO";
		
		String login = externalTask.getVariable("login"); 
		String senha = externalTask.getVariable("senha");
		
		if(login.equals("Quali") && senha.equals("Quali")) {
			resultado = "VALIDO";			
		}
		
		VariableMap variaveis = Variables.createVariables();
		variaveis.put("resposta", resultado);
		variaveis.put("mensagem", "Validado pelo servico REST");
		externalTaskService.complete(externalTask, variaveis);

	}

}
