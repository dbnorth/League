package soccerLeaguePD;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;

/**
 * Dummy {@link EntityManager} used in unit testing.  Implements every
 * method by doing nothing and returning base values.  Intended to be
 * subclassed to override/implement some subset of methods for purposes
 * of testing/verification.
 * 
 * @author nic
 */
public class DummyEntityManager
	implements EntityManager
{
	@Override
	public void clear() {

	}

	@Override
	public void close() {

	}

	@Override
	public boolean contains(Object arg0) {
		return false;
	}

	@Override
	public <T> EntityGraph<T> createEntityGraph(Class<T> arg0) {
		return null;
	}

	@Override
	public EntityGraph<?> createEntityGraph(String arg0) {
		return null;
	}

	@Override
	public Query createNamedQuery(String arg0) {
		return null;
	}

	@Override
	public <T> TypedQuery<T> createNamedQuery(String arg0, Class<T> arg1) {
		return null;
	}

	@Override
	public StoredProcedureQuery createNamedStoredProcedureQuery(String arg0) {
		return null;
	}

	@Override
	public Query createNativeQuery(String arg0) {
		return null;
	}

	@Override
	public Query createNativeQuery(String arg0, Class arg1) {
		return null;
	}

	@Override
	public Query createNativeQuery(String arg0, String arg1) {
		return null;
	}

	@Override
	public Query createQuery(String arg0) {
		return null;
	}

	@Override
	public <T> TypedQuery<T> createQuery(CriteriaQuery<T> arg0) {
		return null;
	}

	@Override
	public Query createQuery(CriteriaUpdate arg0) {
		return null;
	}

	@Override
	public Query createQuery(CriteriaDelete arg0) {
		return null;
	}

	@Override
	public <T> TypedQuery<T> createQuery(String arg0, Class<T> arg1) {
		return null;
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String arg0) {
		return null;
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String arg0, Class... arg1) {
		return null;
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String arg0, String... arg1) {
		return null;
	}

	@Override
	public void detach(Object arg0) {

	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1) {
		return null;
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1, Map<String, Object> arg2) {
		return null;
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1, LockModeType arg2) {
		return null;
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1, LockModeType arg2, Map<String, Object> arg3) {
		return null;
	}

	@Override
	public void flush() {

	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return null;
	}

	@Override
	public Object getDelegate() {
		return null;
	}

	@Override
	public EntityGraph<?> getEntityGraph(String arg0) {
		return null;
	}

	@Override
	public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> arg0) {
		return null;
	}

	@Override
	public EntityManagerFactory getEntityManagerFactory() {
		return null;
	}

	@Override
	public FlushModeType getFlushMode() {
		return null;
	}

	@Override
	public LockModeType getLockMode(Object arg0) {
		return null;
	}

	@Override
	public Metamodel getMetamodel() {
		return null;
	}

	@Override
	public Map<String, Object> getProperties() {
		return null;
	}

	@Override
	public <T> T getReference(Class<T> arg0, Object arg1) {
		return null;
	}

	@Override
	public EntityTransaction getTransaction() {
		return null;
	}

	@Override
	public boolean isJoinedToTransaction() {
		return false;
	}

	@Override
	public boolean isOpen() {
		return false;
	}

	@Override
	public void joinTransaction() {

	}

	@Override
	public void lock(Object arg0, LockModeType arg1) {

	}

	@Override
	public void lock(Object arg0, LockModeType arg1, Map<String, Object> arg2) {

	}

	@Override
	public <T> T merge(T arg0) {
		return null;
	}

	@Override
	public void persist(Object arg0) {

	}

	@Override
	public void refresh(Object arg0) {

	}

	@Override
	public void refresh(Object arg0, Map<String, Object> arg1) {

	}

	@Override
	public void refresh(Object arg0, LockModeType arg1) {

	}

	@Override
	public void refresh(Object arg0, LockModeType arg1, Map<String, Object> arg2) {

	}

	@Override
	public void remove(Object arg0) {

	}

	@Override
	public void setFlushMode(FlushModeType arg0) {

	}

	@Override
	public void setProperty(String arg0, Object arg1) {

	}

	@Override
	public <T> T unwrap(Class<T> arg0) {
		return null;
	}
}
