grammar DistributionRules;

routingRuleSet: routingRuleGroup (routingWaitingRuleGroup)* EOF;

routingWaitingRuleGroup: waitRule routingRuleGroup;

routingRuleGroup: (routingRule)+;

routingRule: ruleAction andSkills order;

ruleAction: 'queue to';

andSkills: skill ('and' skill)*;

skill: '@S:' entity_identifier;

entity_identifier: UUID_OR_HEXA | NUMBER;

order: 'with priority' NUMBER;

waitRule: 'wait' NUMBER;

NUMBER: [1-9]([0-9]*);
UUID_OR_HEXA: ([0-9a-fA-F]+('-')?)+;
WHITESPACE: [ \t\n\r]+ -> skip;
