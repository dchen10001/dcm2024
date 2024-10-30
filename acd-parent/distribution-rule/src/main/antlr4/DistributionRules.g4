grammar DistributionRules;

/* routing rule set is the top rule set */
routingRuleSet: routingRuleGroup (routingWaitingRuleGroup)* EOF;

routingWaitingRuleGroup: waitRule routingRuleGroup;

routingRuleGroup: (routingRule)+;

routingRule: ruleAction (queue_status)? andSkills priority;

/* if we have new action, such as ROUTE_TO, 
* we can add it here as 
* ruleAction: QUEUE_TO | ROUTE_TO;
*/
ruleAction: QUEUE_TO;

andSkills: skillOrSet ('and' skillOrSet)*;

skillOrSet: skill | skillSet;

skillSet: '(' skill (',' skill)+ ')' ;

skill: '@S:' entity_identifier ('level' levelCondition)?;

levelCondition:
           binaryOperator NUMBER
           | sqlOperator NUMBER '..' NUMBER
       ;

binaryOperator: (LESS_THAN 
	| LESS_THAN_EQUAL 
	| EQUAL | NOT_EQUAL 
	| GREATER_THAN | GREATER_THAN_EQUAL);

LESS_THAN: '<';
LESS_THAN_EQUAL: '<=';
EQUAL: '=';
NOT_EQUAL: '<>'|'!=';
GREATER_THAN: '>';
GREATER_THAN_EQUAL: '>=';


sqlOperator: IN | NOT_IN;

IN: 'in';
NOT_IN: 'not in';

entity_identifier: UUID_OR_HEXA | NUMBER;

priority: 'with priority' NUMBER;

waitRule: 'wait' NUMBER;

/* if we have new agent status, such as HIGHER_RANKING, 
* we can add it here as 
* queue_status: LEAST_BUSY | HIGHER_RANKING;
*/
queue_status: LEAST_BUSY;

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
