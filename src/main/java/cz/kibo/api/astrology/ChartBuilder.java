package cz.kibo.api.astrology;

import java.time.LocalDateTime;

public class ChartBuilder {
	
	private final LocalDateTime event;

	public ChartBuilder(LocalDateTime event) {
		super();
		this.event = event;
	}	
	
	public Chart buildChart() {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
	}
	
	public ChartBuilder planets() {
		// TODO
        return this;
	}
}
