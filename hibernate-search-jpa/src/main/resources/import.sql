--
-- JBoss, Home of Professional Open Source
-- Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

INSERT INTO QUOTE (ID, AUTHOR, TEXT) VALUES (1, 'Jane Austen', 'To sit in the shade on a fine day and look upon verdure is the most perfect refreshment.');
INSERT INTO TOPIC (ID, NAME) VALUES (2, 'relaxation');
INSERT INTO TOPIC (ID, NAME) VALUES (3, 'enjoyment');
INSERT INTO QUOTE_TOPIC VALUES (1, 2);
INSERT INTO QUOTE_TOPIC VALUES (1, 3);
INSERT INTO QUOTE (ID, AUTHOR, TEXT) VALUES (2, 'Mark Twain', 'Go to Heaven for the climate, Hell for the company.');
INSERT INTO TOPIC (ID, NAME) VALUES (4, 'humor');
INSERT INTO QUOTE_TOPIC VALUES (2, 4);
INSERT INTO QUOTE (ID, AUTHOR, TEXT) VALUES (3, 'Oscar Wilde', 'True friends stab you in the front.');
INSERT INTO TOPIC (ID, NAME) VALUES (5, 'friendship');
INSERT INTO QUOTE_TOPIC VALUES (1, 5);

