package logic.api.exception;

public class LogicHandlerException extends RuntimeException {

	public LogicHandlerException() {
	}

	public LogicHandlerException(String s) {
		super(s);
	}

	public LogicHandlerException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public LogicHandlerException(Throwable throwable) {
		super(throwable);
	}

	private static final long serialVersionUID = 1L;
}
