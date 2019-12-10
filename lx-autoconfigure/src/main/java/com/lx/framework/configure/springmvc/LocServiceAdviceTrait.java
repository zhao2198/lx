package com.lx.framework.configure.springmvc;

import com.lx.framework.configure.ServiceException;
import com.lx.framework.configure.common.BaseResultCode;
import com.lx.framework.configure.springmvc.utils.ProblemUtil;
import org.apiguardian.api.API;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.general.GeneralAdviceTrait;
import org.zalando.problem.spring.web.advice.http.HttpAdviceTrait;
import org.zalando.problem.spring.web.advice.io.IOAdviceTrait;
import org.zalando.problem.spring.web.advice.routing.RoutingAdviceTrait;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

import static com.lx.framework.configure.common.LxConstants.*;

@API(status = API.Status.STABLE)
public interface LocServiceAdviceTrait extends GeneralAdviceTrait, HttpAdviceTrait, IOAdviceTrait,
    RoutingAdviceTrait {

  @API(status = API.Status.INTERNAL)
  @ExceptionHandler(value = ServiceException.class)
  default ResponseEntity<Problem> handleHnServiceException(
          final ServiceException hnServiceException, final NativeWebRequest request) {
    int code = hnServiceException.getCode();
    if(code < 0) {
      code *= -1;
    }

    if(code < 10000) {
      code += 10000;
    }
    return this.create(hnServiceException,
        ProblemUtil.createProblem(hnServiceException.getMsg(), code),
        request);
  }

  @API(status = API.Status.INTERNAL)
  @ExceptionHandler(value = ConstraintViolationException.class)
  default ResponseEntity<Problem> handleConstraintViolationException(
          final ConstraintViolationException constraintViolationException,
          final NativeWebRequest request) {
    return this.create(constraintViolationException, ProblemUtil.createProblem(
        constraintViolationException.getConstraintViolations().stream()
            .map(ConstraintViolation::getMessage).collect(Collectors.joining(",")),
        CONSTRAINT_VIOLATION_ERROR_CODE), request);
  }

  @API(status = API.Status.INTERNAL)
  @ExceptionHandler(value = BindException.class)
  default ResponseEntity<Problem> handleBindException(final BindException bindException,
                                                      final NativeWebRequest request) {
    List<FieldError> fieldErrors = bindException.getBindingResult().getFieldErrors();
    return this.create(bindException, ProblemUtil.createProblem(
        fieldErrors.stream().map(e -> e.getField() + ":" + e.getDefaultMessage())
            .collect(Collectors.joining(",")), BIND_ERROR_CODE), request);
  }

  @API(status = API.Status.INTERNAL)
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  default ResponseEntity<Problem> handleMethodArgumentNotValidException(
          final MethodArgumentNotValidException methodArgumentNotValidException,
          final NativeWebRequest request) {
    List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
    return this.create(methodArgumentNotValidException, ProblemUtil.createProblem(
        fieldErrors.stream().map(e -> e.getField() + ":" + e.getDefaultMessage())
            .collect(Collectors.joining(",")), METHOD_ARGUMENT_NOT_VALID_ERROR_CODE), request);
  }

  @API(status = API.Status.INTERNAL)
  @ExceptionHandler(value = Throwable.class)
  default ResponseEntity<Problem> handleThrowable(final Throwable throwable, final NativeWebRequest request) {
    return this.create(throwable, ProblemUtil.createProblem(BaseResultCode.UNKNOWN_THROWABLE_EXCEPTION_CODE.getMsg(),
        BaseResultCode.UNKNOWN_THROWABLE_EXCEPTION_CODE.getCode(), Status.INTERNAL_SERVER_ERROR), request);
  }
}

