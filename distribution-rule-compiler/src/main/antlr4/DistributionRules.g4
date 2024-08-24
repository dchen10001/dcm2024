grammar DistributionRules;

rules: rule ('wait' NUMBER rule)+;

rule: 'queue' 'to' skills 'with' 'priority' NUMBER;

skills: skill ('and' skill)+;

skill: '@S:' entity_identifier;

entity_identifier: UUID_OR_HEXA | NUMBER;

UUID_OR_HEXA: ([0-9a-fA-F]+('-')?)+;
NUMBER: [1-9][0=9]*;
WHITESPACE: [ \t\n\r]+ -> skip;
