package com.nice.dcm.simulation.distribution.rule.action;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
public class RoutingGroupActionImpl implements RoutingGroupAction {
	private long waitAfterSeconds;
    private final  List<QueueToAction> actions;
}
