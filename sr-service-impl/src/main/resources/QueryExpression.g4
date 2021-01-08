grammar QueryExpression;


fragment LETTER:       [A-Z_];
fragment IPV6_OCTECT:  [0-9A-F][0-9A-F][0-9A-F][0-9A-F];
//IPV4_OCTECT:           [0-9]?[0-9]?[0-9];
fragment DEC_DOT_DEC:  (DEC_DIGIT+ '.' DEC_DIGIT+ |  DEC_DIGIT+ '.' | '.' DEC_DIGIT+);
fragment HEX_DIGIT:    [0-9A-F];
fragment DEC_DIGIT:    [0-9];

fragment OCTAL_ESC
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment ESC_SEQ
//    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
//    |
    : UNICODE_ESC
    |   OCTAL_ESC
    ;

fragment UNICODE_ESC
    :   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;

WS : [\t\r\n ]+->skip;

LITERAL_NULL:        ('null' | 'NULL');
LITERAL_TRUE:        ('true' | 'TRUE');
LITERAL_FALSE:       ('false' | 'FALSE');
DECIMAL:             DEC_DIGIT+;

OP_CONTAINS:         'contains';
OP_LIKE:             'like';
IDENTIFIER:          ( [a-zA-Z_#] ) ( [a-zA-Z_#$@0-9.] )*;
REAL:                (DECIMAL | DEC_DOT_DEC) ('E' [+-]? DEC_DIGIT+);
LR_BRACKET:          '(';
RR_BRACKET:          ')';
EQUAL:               '=';
GREATER:             '>';
LESS:                '<';
EXCLAMATION:         '!';
DOT:                 '.';
UNDERLINE:           '_';
LOGICAL_AND:         '&&';
LOGICAL_OR:          '||';
SPACE:               [ \t\r\n]+;
STRING:              '\''( ESC_SEQ | ~('\\'|'\'') )*'\'';

operator:        GREATER | LESS | EQUAL | EXCLAMATION EQUAL | GREATER EQUAL | LESS EQUAL | OP_LIKE | OP_CONTAINS;
field: IDENTIFIER;
value: (STRING | DECIMAL | LITERAL_NULL | LITERAL_TRUE | LITERAL_FALSE | IDENTIFIER);
logical_op: LOGICAL_AND | LOGICAL_OR;
//prog: expr+;
condition: field operator value;

expr: expr (logical_op expr)+
    | LR_BRACKET expr RR_BRACKET
    | condition
    ;

