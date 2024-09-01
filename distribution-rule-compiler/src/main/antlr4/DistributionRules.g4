grammar DistributionRules;

routingRuleSet: routingRuleGroup (routingWaitingRuleGroup)* EOF;

routingWaitingRuleGroup: waitRule routingRuleGroup;

routingRuleGroup: (routingRule)+;

routingRule: ruleAction (agent_status)? andSkills order;

ruleAction: QUEUE_TO | ROUTE_TO;

andSkills: skill ('and' skill)*;

skill: '@S:' entity_identifier;

entity_identifier: UUID_OR_HEXA | NUMBER;

order: 'with priority' NUMBER;

waitRule: 'wait' NUMBER;

agent_status: LEAST_BUSY|HIGHER_RANKING;

// Agent status
LEAST_BUSY: 'least busy of';
HIGHER_RANKING: 'higher ranking of';

// rule Action
QUEUE_TO: 'queue to';
ROUTE_TO: 'route to';

NUMBER: [1-9]([0-9]*);
UUID_OR_HEXA: ([0-9a-fA-F]+('-')?)+;
WHITESPACE: [ \t\n\r]+ -> skip;
