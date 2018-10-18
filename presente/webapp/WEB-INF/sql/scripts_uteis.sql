--atualizar todas as matriculas para não enviar email e sms no registro
UPDATE matricula SET bol_enviar_email_registro=false, bol_enviar_sms_registro=false;

--atualizar todas as matriculas para não enviar email no registro
UPDATE matricula SET bol_enviar_email_registro=false;

--atualizar todas as matriculas para não enviar sms no registro
UPDATE matricula SET bol_enviar_sms_registro=false;