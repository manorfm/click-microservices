package br.com.clicks.domain.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ClickRepositoryImpl implements ClickRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
/*	@Override
	public List<Click> findByUserAndPeriod(long pis, int month, int year) {
		QClock click = QClock.clock;
		
		return new JPAQuery<Click>(entityManager)
			.from(click)
			.where(
					click.timer.year().eq(year)
				.and(click.timer.month().eq(month))
				.and(click.user.pis.eq(pis))
			)
			.orderBy(click.timer.asc())
			.fetch();
	}*/

}
