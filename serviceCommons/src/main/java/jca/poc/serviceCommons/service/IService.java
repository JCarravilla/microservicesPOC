package jca.poc.serviceCommons.service;

/**
 * Created by jcarravilla on 6/11/17.
 */
public interface IService<T, R> {
    public T executeService(R param) throws Exception;
}
