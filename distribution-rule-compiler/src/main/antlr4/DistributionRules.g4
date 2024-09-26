grammar DistributionRules;

routingRuleSet: routingRuleGroup (routingWaitingRuleGroup)* EOF;

routingWaitingRuleGroup: waitRule routingRuleGroup;

routingRuleGroup: (routingRule)+;

routingRule: ruleAction (agent_status)? andSkills order;

/* if we have new action, such as ROUTE_TO, 
* we can add it here as 
* ruleAction: QUEUE_TO | ROUTE_TO;
*/
ruleAction: QUEUE_TO;

andSkills: skillOrSet ('and' skillOrSet)*;

skillOrSet: skill | skillSet;

skillSet: '(' skill (',' skill)+ ')' ;

skill: '@S:' entity_identifier;

entity_identifier: UUID_OR_HEXA | NUMBER;

order: 'with priority' NUMBER;

waitRule: 'wait' NUMBER;

/* if we have new agent status, such as HIGHER_RANKING, 
* we can add it here as 
* agent_status: LEAST_BUSY | HIGHER_RANKING;
*/
agent_status: LEAST_BUSY;

// Agent status
LEAST_BUSY: 'least busy of';
// new agent status value can be added here, such as HIGHER_RANKING
//HIGHER_RANKING: 'higher ranking of';

// rule Action
QUEUE_TO: 'queue to';
// new action value can be added here, such as ROUTE_TO
//ROUTE_TO: 'route to';

NUMBER: [1-9]([0-9]*);
UUID_OR_HEXA: ([0-9a-fA-F]+('-')?)+;
WHITESPACE: [ \t\n\r]+ -> skip;
