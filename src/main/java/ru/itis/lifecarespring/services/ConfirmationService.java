package ru.itis.lifecarespring.services;

public interface ConfirmationService {
	boolean emailConfirm(String confirmCode);
	boolean smsConfirm(long confirmCode);
}
