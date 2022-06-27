--
-- PostgreSQL database dump
--

-- Dumped from database version 13.6
-- Dumped by pg_dump version 13.6

-- Started on 2022-04-28 02:30:07

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 3062 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 211 (class 1259 OID 16513)
-- Name: labels; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.labels (
    label_id integer NOT NULL,
    label_title character varying(50) NOT NULL,
    group_task integer DEFAULT 0 NOT NULL,
    label_color character varying(7) DEFAULT '#AED6DC'::character varying NOT NULL,
    user_label integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.labels OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16511)
-- Name: labels_label_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.labels_label_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.labels_label_id_seq OWNER TO postgres;

--
-- TOC entry 3063 (class 0 OID 0)
-- Dependencies: 210
-- Name: labels_label_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.labels_label_id_seq OWNED BY public.labels.label_id;


--
-- TOC entry 201 (class 1259 OID 16397)
-- Name: singleuser; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.singleuser (
    user_id integer NOT NULL,
    user_name character varying(50) NOT NULL,
    user_login character varying(40) NOT NULL,
    user_password character varying(40) NOT NULL,
    user_role integer,
    group_id integer,
    is_active integer DEFAULT 1 NOT NULL,
    user_foto character varying(500)
);


ALTER TABLE public.singleuser OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16395)
-- Name: singleuser_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.singleuser_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.singleuser_user_id_seq OWNER TO postgres;

--
-- TOC entry 3064 (class 0 OID 0)
-- Dependencies: 200
-- Name: singleuser_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.singleuser_user_id_seq OWNED BY public.singleuser.user_id;


--
-- TOC entry 203 (class 1259 OID 16405)
-- Name: task; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.task (
    case_id integer NOT NULL,
    case_title character varying(30) NOT NULL,
    case_date date NOT NULL,
    case_due_date timestamp without time zone NOT NULL,
    case_description character varying(500),
    user_id integer NOT NULL,
    is_active integer DEFAULT 1 NOT NULL,
    preparation_time time without time zone
);


ALTER TABLE public.task OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16403)
-- Name: task_case_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.task_case_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.task_case_id_seq OWNER TO postgres;

--
-- TOC entry 3065 (class 0 OID 0)
-- Dependencies: 202
-- Name: task_case_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.task_case_id_seq OWNED BY public.task.case_id;


--
-- TOC entry 209 (class 1259 OID 16488)
-- Name: task_label; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.task_label (
    case_id integer NOT NULL,
    label_id integer NOT NULL
);


ALTER TABLE public.task_label OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16416)
-- Name: user_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_role (
    user_role_id integer NOT NULL,
    user_role_name character varying(20) NOT NULL,
    user_role_description character varying(500)
);


ALTER TABLE public.user_role OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16414)
-- Name: user_role_user_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_role_user_role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_role_user_role_id_seq OWNER TO postgres;

--
-- TOC entry 3066 (class 0 OID 0)
-- Dependencies: 204
-- Name: user_role_user_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_role_user_role_id_seq OWNED BY public.user_role.user_role_id;


--
-- TOC entry 208 (class 1259 OID 16469)
-- Name: user_workgroup; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_workgroup (
    user_id integer,
    group_id integer
);


ALTER TABLE public.user_workgroup OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16427)
-- Name: workgroup; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.workgroup (
    group_id integer NOT NULL,
    group_name character varying(40) NOT NULL,
    group_leader integer,
    group_description character varying(500),
    group_label integer
);


ALTER TABLE public.workgroup OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16425)
-- Name: workgroup_group_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.workgroup_group_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.workgroup_group_id_seq OWNER TO postgres;

--
-- TOC entry 3067 (class 0 OID 0)
-- Dependencies: 206
-- Name: workgroup_group_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.workgroup_group_id_seq OWNED BY public.workgroup.group_id;


--
-- TOC entry 2892 (class 2604 OID 16516)
-- Name: labels label_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.labels ALTER COLUMN label_id SET DEFAULT nextval('public.labels_label_id_seq'::regclass);


--
-- TOC entry 2886 (class 2604 OID 16400)
-- Name: singleuser user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.singleuser ALTER COLUMN user_id SET DEFAULT nextval('public.singleuser_user_id_seq'::regclass);


--
-- TOC entry 2888 (class 2604 OID 16408)
-- Name: task case_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task ALTER COLUMN case_id SET DEFAULT nextval('public.task_case_id_seq'::regclass);


--
-- TOC entry 2890 (class 2604 OID 16419)
-- Name: user_role user_role_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_role ALTER COLUMN user_role_id SET DEFAULT nextval('public.user_role_user_role_id_seq'::regclass);


--
-- TOC entry 2891 (class 2604 OID 16430)
-- Name: workgroup group_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workgroup ALTER COLUMN group_id SET DEFAULT nextval('public.workgroup_group_id_seq'::regclass);


--
-- TOC entry 3056 (class 0 OID 16513)
-- Dependencies: 211
-- Data for Name: labels; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.labels VALUES (1, 'My Task', 0, '#AED6DC', 0);
INSERT INTO public.labels VALUES (10, 'interesting', 0, '#AED6DC', 0);
INSERT INTO public.labels VALUES (15, 'easy', 0, '#10da8d', 0);
INSERT INTO public.labels VALUES (2, 'group for sport', 1, '#958dff', 0);
INSERT INTO public.labels VALUES (3, 'Group ', 1, '#958dff', 0);
INSERT INTO public.labels VALUES (4, 'fghjk', 1, '#958dff', 0);
INSERT INTO public.labels VALUES (5, 'gcfbhm', 1, '#958dff', 0);
INSERT INTO public.labels VALUES (11, 'Natasha''s group', 1, '#958dff', 0);
INSERT INTO public.labels VALUES (12, 'Natasha''s group2', 1, '#958dff', 0);
INSERT INTO public.labels VALUES (14, 'Natasha''s group4', 1, '#958dff', 0);
INSERT INTO public.labels VALUES (6, 'important', 0, '#e47193', 0);
INSERT INTO public.labels VALUES (7, 'study', 0, '#e4b672', 0);
INSERT INTO public.labels VALUES (8, 'hobby', 0, '#c29ad4', 0);
INSERT INTO public.labels VALUES (9, 'entertainment', 0, '#72dce4', 0);


--
-- TOC entry 3046 (class 0 OID 16397)
-- Dependencies: 201
-- Data for Name: singleuser; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.singleuser VALUES (3, 'Misha', 'misha456', 'pas123', 1, 1, 1, NULL);
INSERT INTO public.singleuser VALUES (5, 'Denis', 'denis456', 'pas123', 2, 2, 1, NULL);
INSERT INTO public.singleuser VALUES (25, 'Natasha', 'nata123', 'nata123', 1, 2, 1, NULL);
INSERT INTO public.singleuser VALUES (31, 'Gosha', 'gosha', 'gosha', 1, 2, 1, NULL);
INSERT INTO public.singleuser VALUES (32, 'fg', 'mylogin', 'mypassword', 1, 2, 1, NULL);
INSERT INTO public.singleuser VALUES (35, 'Natasha', 'nata1234567890', '1234567', 1, 1, 1, NULL);
INSERT INTO public.singleuser VALUES (36, 'Anna', 'kjubylkzfyys', '123456', 1, 2, 1, NULL);
INSERT INTO public.singleuser VALUES (30, 'oiu', 'iuy', 'oiu', 1, 0, 1, NULL);
INSERT INTO public.singleuser VALUES (33, 'jygjg', 'lalalala3', '123456', 1, 0, 1, NULL);
INSERT INTO public.singleuser VALUES (29, 'asdf', 'sdfghjkl', 'asdf', 1, 0, 1, NULL);
INSERT INTO public.singleuser VALUES (4, 'Kate', 'kate2003', 'katekate', 2, 2, 1, NULL);
INSERT INTO public.singleuser VALUES (37, 'Trixi', 'trixi123', '123456', 1, 0, 1, NULL);
INSERT INTO public.singleuser VALUES (38, 'Jack', 'jack123', '123456', 1, 0, 1, NULL);
INSERT INTO public.singleuser VALUES (39, 'admin', 'admin', 'admin', 3, NULL, 1, NULL);
INSERT INTO public.singleuser VALUES (40, 'Anna', 'anna123', '123456', NULL, NULL, 1, NULL);
INSERT INTO public.singleuser VALUES (6, 'Anna', 'anna03.04', '654321', 1, 2, 0, NULL);
INSERT INTO public.singleuser VALUES (2, 'Dima', 'dima123', '123456', 1, 0, 1, NULL);
INSERT INTO public.singleuser VALUES (1, 'Alena', 'lalalalala', 'password', 1, 1, 1, NULL);


--
-- TOC entry 3048 (class 0 OID 16405)
-- Dependencies: 203
-- Data for Name: task; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.task VALUES (33, 'name', '2022-03-23', '2022-03-26 00:00:00', 'dc', 1, 1, '00:00:00');
INSERT INTO public.task VALUES (34, 'name', '2022-04-24', '2022-04-30 00:00:00', 'dc', 1, 1, '00:00:00');
INSERT INTO public.task VALUES (35, 'gfghs', '2022-04-16', '2022-04-17 00:00:00', 'gfhgdsss', 1, 1, '00:00:00');
INSERT INTO public.task VALUES (8, 'Ляляля', '2002-03-26', '2002-03-27 00:00:00', 'ляляляля', 32, 1, '00:00:00');
INSERT INTO public.task VALUES (27, 'uytrea', '2022-03-26', '2022-03-27 00:00:00', 'ythgrf', 31, 1, '00:00:00');
INSERT INTO public.task VALUES (28, 'name', '2022-03-27', '2022-03-30 00:00:00', 'description', 32, 1, '00:00:00');
INSERT INTO public.task VALUES (31, 'nfghh', '2022-03-30', '2022-03-31 00:00:00', 'lalala', 37, 1, '00:00:00');
INSERT INTO public.task VALUES (26, 'lbvc', '2022-03-26', '2022-03-27 00:00:00', 'ythgrf', 29, 1, '00:00:00');
INSERT INTO public.task VALUES (30, ' hgh', '2022-03-29', '2022-03-30 00:00:00', 'dc', 30, 1, '00:00:00');
INSERT INTO public.task VALUES (36, 'New Case 2', '2022-04-04', '2022-04-06 00:00:00', 'New description', 1, 1, '00:00:00');
INSERT INTO public.task VALUES (37, 'Task from Kate', '2022-04-04', '2022-04-06 00:00:00', '', 4, 1, '00:00:00');
INSERT INTO public.task VALUES (4, 'Сделать Тест', '2022-03-22', '2022-03-23 00:00:00', 'не меньше 4', 5, 1, '00:00:00');
INSERT INTO public.task VALUES (1, 'Погладить кота', '2022-03-21', '2022-03-22 00:00:00', 'Можно и не один раз', 1, 1, '00:00:00');
INSERT INTO public.task VALUES (2, 'Полить цветы', '2022-03-22', '2022-03-23 00:00:00', 'все пожалуйста ', 1, 1, '00:00:00');
INSERT INTO public.task VALUES (3, 'Написать курсовую', '2022-03-21', '2022-04-22 00:00:00', 'Удачи', 2, 1, '00:00:00');
INSERT INTO public.task VALUES (6, 'Помыть кота', '2022-11-10', '2022-11-15 00:00:00', 'Шампунь на полке', 3, 1, '00:00:00');
INSERT INTO public.task VALUES (7, 'Прочитать книгу', '2002-11-15', '2002-11-25 00:00:00', '"Двадцать тысяч лье под водой" ', 4, 1, '00:00:00');
INSERT INTO public.task VALUES (38, 'Task from Kate2', '2022-04-04', '2022-04-05 00:00:00', 'Task ', 4, 1, '00:00:00');
INSERT INTO public.task VALUES (39, 'Task from Kate4', '2022-04-01', '2022-04-04 00:00:00', '', 1, 1, '00:00:00');
INSERT INTO public.task VALUES (40, 'Test', '2022-04-16', '2022-04-17 00:00:00', 'Test', 1, 1, '00:00:00');
INSERT INTO public.task VALUES (41, 'gfghs', '2022-04-16', '2022-04-17 00:00:00', 'gfhgdsss', 1, 1, '00:00:00');
INSERT INTO public.task VALUES (42, 'today', '2022-04-04', '2022-04-05 00:00:00', '', 1, 1, '00:00:00');
INSERT INTO public.task VALUES (15, 'req.getParameter', '2022-03-26', '2022-03-28 00:00:00', 'case_description', 1, 1, '00:00:00');
INSERT INTO public.task VALUES (43, 'Test today', '2022-04-01', '2022-04-05 00:00:00', '', 1, 1, '00:00:00');
INSERT INTO public.task VALUES (86, 'Go to university', '2022-04-25', '2022-04-25 09:00:00', 'You don''t have to go to the first class ', 2, 1, '00:10:00');
INSERT INTO public.task VALUES (45, 'Task from Kate5', '2022-04-02', '2022-04-06 00:00:00', '', 2, 1, '00:00:00');
INSERT INTO public.task VALUES (46, 'Tack', '2022-04-08', '2022-04-09 00:00:00', '', 1, 1, '00:00:00');
INSERT INTO public.task VALUES (47, 'lalala', '2022-04-09', '2022-04-10 00:00:00', '', 2, 1, '00:00:00');
INSERT INTO public.task VALUES (48, 'timed task', '2022-04-09', '2022-04-10 00:00:00', 'New description', 2, 1, '00:00:00');
INSERT INTO public.task VALUES (49, 'Timestamp', '2022-04-09', '2022-04-10 00:00:00', '', 2, 1, '00:00:00');
INSERT INTO public.task VALUES (50, 'New time Case', '2022-04-09', '2022-04-10 00:00:00', 'time', 2, 1, '00:00:00');
INSERT INTO public.task VALUES (51, 'dfghj', '2022-04-10', '2022-04-11 03:30:00', '', 2, 1, '00:00:00');
INSERT INTO public.task VALUES (52, 'Run', '2022-04-09', '2022-04-11 08:00:00', '', 2, 1, '00:00:00');
INSERT INTO public.task VALUES (53, 'Run2', '2022-04-05', '2022-04-11 08:00:00', '', 2, 1, '00:00:00');
INSERT INTO public.task VALUES (54, 'Run2', '2022-04-09', '2022-04-11 08:10:00', '', 2, 1, '00:00:00');
INSERT INTO public.task VALUES (87, 'Put the phone on charge', '2022-04-25', '2022-04-25 01:00:00', '', 2, 1, '00:00:00');
INSERT INTO public.task VALUES (56, 'Run3', '2022-04-06', '2022-04-11 07:08:00', '', 2, 1, '00:00:00');
INSERT INTO public.task VALUES (57, 'Run4', '2022-04-07', '2022-04-08 03:16:00', '', 2, 1, '00:00:00');
INSERT INTO public.task VALUES (58, 'Run5', '2022-04-06', '2022-04-11 03:18:00', '', 2, 1, '00:00:00');
INSERT INTO public.task VALUES (88, 'Wash the cat', '2022-04-25', '2022-04-25 22:00:00', 'Shampoo for furry cats', 2, 1, '02:00:00');
INSERT INTO public.task VALUES (61, 'Task from Dima', '2022-04-09', '2022-04-11 05:58:00', '', 1, 1, '00:00:00');
INSERT INTO public.task VALUES (62, 'Time', '2022-04-11', '2022-04-11 20:10:00', '', 2, 1, '00:00:00');
INSERT INTO public.task VALUES (64, 'zssvgugvl', '2022-04-11', '2022-04-12 20:24:00', '', 2, 1, '00:00:00');
INSERT INTO public.task VALUES (65, 'sdfhgjhkjlk', '2022-04-11', '2022-04-12 20:41:00', '', 4, 1, '00:00:00');
INSERT INTO public.task VALUES (68, 'Task from Kate4', '2022-04-15', '2022-04-15 02:49:00', '', 2, 1, '00:00:00');
INSERT INTO public.task VALUES (84, 'Make labs', '2022-04-25', '2022-04-26 20:30:00', 'Second Lab on IDA', 2, 1, '00:15:00');
INSERT INTO public.task VALUES (85, 'Go to sleep', '2022-04-25', '2022-04-26 23:00:00', 'The sooner the better ', 2, 0, '12:00:00');
INSERT INTO public.task VALUES (69, 'New Case', '2022-04-15', '2022-04-15 03:57:00', '', 2, 1, '02:00:00');
INSERT INTO public.task VALUES (73, 'New Case', '2022-04-15', '2022-04-16 04:01:00', '', 2, 1, '00:00:00');
INSERT INTO public.task VALUES (67, 'New Case', '2022-04-15', '2022-04-16 05:32:00', '', 2, 1, '00:00:00');
INSERT INTO public.task VALUES (78, 'Task from Kate4', '2022-04-18', '2022-04-18 01:14:00', '', 2, 1, '01:01:00');
INSERT INTO public.task VALUES (80, 'Task from Kate4', '2022-04-20', '2022-04-20 23:44:00', '', 4, 1, '00:00:00');
INSERT INTO public.task VALUES (81, 'New Case', '2022-04-23', '2022-04-23 20:00:00', 'Description ', 2, 1, '00:00:00');
INSERT INTO public.task VALUES (79, 'Run2', '2022-04-20', '2022-04-20 22:48:00', '', 2, 1, '00:20:00');
INSERT INTO public.task VALUES (82, 'Wash the floors', '2022-04-23', '2022-04-24 16:00:00', 'On the first and second floor', 2, 1, '02:00:00');
INSERT INTO public.task VALUES (44, 'New Case', '2022-04-01', '2022-04-24 17:38:00', '', 2, 0, '00:00:00');
INSERT INTO public.task VALUES (72, 'New Case', '2022-04-15', '2022-04-15 04:46:00', '', 2, 0, '00:00:00');
INSERT INTO public.task VALUES (74, 'New Casentime', '2022-04-15', '2022-04-15 15:01:00', '', 2, 1, '00:10:00');
INSERT INTO public.task VALUES (89, 'Watch the show', '2022-04-25', '2022-04-25 23:50:00', 'Disappointment', 2, 1, '00:00:00');
INSERT INTO public.task VALUES (83, 'Watering the flowers', '2022-04-25', '2022-04-27 10:40:00', 'Especially the aloe on the shelf', 2, 1, '00:20:00');
INSERT INTO public.task VALUES (90, 'Watch the show', '2022-04-25', '2022-04-25 23:59:00', 'Disappointment', 2, 1, '00:10:00');
INSERT INTO public.task VALUES (94, 'Drink tea', '2022-04-26', '2022-04-27 03:10:00', '', 2, 1, '00:10:00');
INSERT INTO public.task VALUES (93, 'Make tea', '2022-04-26', '2022-04-26 02:00:00', '', 2, 0, '10:00:00');
INSERT INTO public.task VALUES (95, 'Do homework', '2022-04-26', '2022-04-26 03:00:00', 'Task for group', 2, 1, '00:20:00');
INSERT INTO public.task VALUES (91, 'Drink some water', '2022-04-25', '2022-04-26 03:00:00', '', 2, 0, '00:20:00');
INSERT INTO public.task VALUES (97, 'Check labels', '2022-04-28', '2022-04-28 19:00:00', '', 2, 1, '03:00:00');
INSERT INTO public.task VALUES (98, 'Wait for the update', '2022-04-28', '2022-04-29 16:10:00', '', 4, 1, '04:00:00');


--
-- TOC entry 3054 (class 0 OID 16488)
-- Dependencies: 209
-- Data for Name: task_label; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.task_label VALUES (45, 1);
INSERT INTO public.task_label VALUES (62, 2);
INSERT INTO public.task_label VALUES (64, 4);
INSERT INTO public.task_label VALUES (65, 4);
INSERT INTO public.task_label VALUES (72, 8);
INSERT INTO public.task_label VALUES (73, 8);
INSERT INTO public.task_label VALUES (67, 6);
INSERT INTO public.task_label VALUES (78, 10);
INSERT INTO public.task_label VALUES (80, 1);
INSERT INTO public.task_label VALUES (3, 6);
INSERT INTO public.task_label VALUES (58, 8);
INSERT INTO public.task_label VALUES (47, 1);
INSERT INTO public.task_label VALUES (82, 15);
INSERT INTO public.task_label VALUES (74, 15);
INSERT INTO public.task_label VALUES (86, 7);
INSERT INTO public.task_label VALUES (88, 6);
INSERT INTO public.task_label VALUES (90, 6);
INSERT INTO public.task_label VALUES (93, 15);
INSERT INTO public.task_label VALUES (85, 10);
INSERT INTO public.task_label VALUES (95, 5);
INSERT INTO public.task_label VALUES (84, 8);
INSERT INTO public.task_label VALUES (69, 9);
INSERT INTO public.task_label VALUES (83, 15);
INSERT INTO public.task_label VALUES (98, 4);


--
-- TOC entry 3050 (class 0 OID 16416)
-- Dependencies: 205
-- Data for Name: user_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_role VALUES (1, 'standard user', NULL);
INSERT INTO public.user_role VALUES (2, 'group leader ', NULL);
INSERT INTO public.user_role VALUES (3, 'admin', NULL);


--
-- TOC entry 3053 (class 0 OID 16469)
-- Dependencies: 208
-- Data for Name: user_workgroup; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_workgroup VALUES (3, 1);
INSERT INTO public.user_workgroup VALUES (5, 2);
INSERT INTO public.user_workgroup VALUES (6, 2);
INSERT INTO public.user_workgroup VALUES (25, 2);
INSERT INTO public.user_workgroup VALUES (31, 2);
INSERT INTO public.user_workgroup VALUES (32, 2);
INSERT INTO public.user_workgroup VALUES (35, 1);
INSERT INTO public.user_workgroup VALUES (36, 2);
INSERT INTO public.user_workgroup VALUES (4, 2);
INSERT INTO public.user_workgroup VALUES (4, 1);
INSERT INTO public.user_workgroup VALUES (2, 2);
INSERT INTO public.user_workgroup VALUES (2, 7);
INSERT INTO public.user_workgroup VALUES (1, 21);
INSERT INTO public.user_workgroup VALUES (2, 21);
INSERT INTO public.user_workgroup VALUES (2, 1);
INSERT INTO public.user_workgroup VALUES (4, 7);
INSERT INTO public.user_workgroup VALUES (2, 22);
INSERT INTO public.user_workgroup VALUES (2, 23);
INSERT INTO public.user_workgroup VALUES (1, 1);
INSERT INTO public.user_workgroup VALUES (2, 22);
INSERT INTO public.user_workgroup VALUES (2, 22);
INSERT INTO public.user_workgroup VALUES (2, 24);
INSERT INTO public.user_workgroup VALUES (2, 25);
INSERT INTO public.user_workgroup VALUES (1, 25);
INSERT INTO public.user_workgroup VALUES (2, 26);
INSERT INTO public.user_workgroup VALUES (4, 26);
INSERT INTO public.user_workgroup VALUES (4, 27);
INSERT INTO public.user_workgroup VALUES (2, 27);
INSERT INTO public.user_workgroup VALUES (1, 28);
INSERT INTO public.user_workgroup VALUES (2, 28);
INSERT INTO public.user_workgroup VALUES (1, 29);
INSERT INTO public.user_workgroup VALUES (2, 29);
INSERT INTO public.user_workgroup VALUES (1, 31);
INSERT INTO public.user_workgroup VALUES (4, 31);


--
-- TOC entry 3052 (class 0 OID 16427)
-- Dependencies: 207
-- Data for Name: workgroup; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.workgroup VALUES (0, 'no', NULL, NULL, NULL);
INSERT INTO public.workgroup VALUES (1, 'Group1', 4, 'group for study', NULL);
INSERT INTO public.workgroup VALUES (2, 'Group2', 5, 'lalala', NULL);
INSERT INTO public.workgroup VALUES (8, 'My Group 2', 2, 'Dima''s group2', NULL);
INSERT INTO public.workgroup VALUES (9, 'My Group 2', 2, 'Dima''s group2', NULL);
INSERT INTO public.workgroup VALUES (10, 'My Group 2', 2, 'Dima''s group2', NULL);
INSERT INTO public.workgroup VALUES (11, 'My Group 2', 2, 'Dima''s group2', NULL);
INSERT INTO public.workgroup VALUES (12, 'My Group 3', 2, 'Dima''s group3', NULL);
INSERT INTO public.workgroup VALUES (13, 'Alena''s group', 1, 'my group', NULL);
INSERT INTO public.workgroup VALUES (14, 'Dima''s group', 2, 'my group', NULL);
INSERT INTO public.workgroup VALUES (15, 'Alena''s group', 1, 'my group', NULL);
INSERT INTO public.workgroup VALUES (16, 'My Group 3', 2, 'Dima''s group', NULL);
INSERT INTO public.workgroup VALUES (17, 'My Group 3', 2, 'Dima''s group', NULL);
INSERT INTO public.workgroup VALUES (18, 'Kate', 4, 'Kate', NULL);
INSERT INTO public.workgroup VALUES (19, 'My group', 2, 'Kate', NULL);
INSERT INTO public.workgroup VALUES (20, 'Test', 2, 'test', NULL);
INSERT INTO public.workgroup VALUES (21, 'Test Alena', 1, 'test', NULL);
INSERT INTO public.workgroup VALUES (7, 'My group', 4, 'Dima''s group', NULL);
INSERT INTO public.workgroup VALUES (23, 'New 2', 4, 'Dima''s group', NULL);
INSERT INTO public.workgroup VALUES (22, 'New', 2, 'group', NULL);
INSERT INTO public.workgroup VALUES (24, 'group for sport', 2, 'live', 2);
INSERT INTO public.workgroup VALUES (27, 'gcfbhm', 4, 'oiujhv', 5);
INSERT INTO public.workgroup VALUES (25, 'Group ', 1, 'Dima''s group', 3);
INSERT INTO public.workgroup VALUES (31, 'Natasha''s group4', 1, '', 14);
INSERT INTO public.workgroup VALUES (26, 'fghjk', 2, 'Dima''s group', 4);
INSERT INTO public.workgroup VALUES (29, 'Natasha''s group2', 1, '', 12);
INSERT INTO public.workgroup VALUES (28, 'Natasha''s group', 4, '', 11);


--
-- TOC entry 3068 (class 0 OID 0)
-- Dependencies: 210
-- Name: labels_label_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.labels_label_id_seq', 16, true);


--
-- TOC entry 3069 (class 0 OID 0)
-- Dependencies: 200
-- Name: singleuser_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.singleuser_user_id_seq', 40, true);


--
-- TOC entry 3070 (class 0 OID 0)
-- Dependencies: 202
-- Name: task_case_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.task_case_id_seq', 98, true);


--
-- TOC entry 3071 (class 0 OID 0)
-- Dependencies: 204
-- Name: user_role_user_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_role_user_role_id_seq', 1, false);


--
-- TOC entry 3072 (class 0 OID 0)
-- Dependencies: 206
-- Name: workgroup_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.workgroup_group_id_seq', 31, true);


--
-- TOC entry 2905 (class 2606 OID 16518)
-- Name: labels labels_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.labels
    ADD CONSTRAINT labels_pkey PRIMARY KEY (label_id);


--
-- TOC entry 2897 (class 2606 OID 16402)
-- Name: singleuser singleuser_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.singleuser
    ADD CONSTRAINT singleuser_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2899 (class 2606 OID 16413)
-- Name: task task_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_pkey PRIMARY KEY (case_id);


--
-- TOC entry 2901 (class 2606 OID 16424)
-- Name: user_role user_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (user_role_id);


--
-- TOC entry 2903 (class 2606 OID 16432)
-- Name: workgroup workgroup_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workgroup
    ADD CONSTRAINT workgroup_pkey PRIMARY KEY (group_id);


--
-- TOC entry 2907 (class 2606 OID 16496)
-- Name: singleuser singleuser_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.singleuser
    ADD CONSTRAINT singleuser_fk FOREIGN KEY (group_id) REFERENCES public.workgroup(group_id);


--
-- TOC entry 2906 (class 2606 OID 16456)
-- Name: singleuser singleuser_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.singleuser
    ADD CONSTRAINT singleuser_fk_1 FOREIGN KEY (user_role) REFERENCES public.user_role(user_role_id);


--
-- TOC entry 2908 (class 2606 OID 16461)
-- Name: task task_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_fk FOREIGN KEY (user_id) REFERENCES public.singleuser(user_id);


--
-- TOC entry 2913 (class 2606 OID 16491)
-- Name: task_label task_label_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task_label
    ADD CONSTRAINT task_label_fk FOREIGN KEY (case_id) REFERENCES public.task(case_id);


--
-- TOC entry 2914 (class 2606 OID 16524)
-- Name: task_label task_label_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task_label
    ADD CONSTRAINT task_label_fk_1 FOREIGN KEY (label_id) REFERENCES public.labels(label_id);


--
-- TOC entry 2911 (class 2606 OID 16472)
-- Name: user_workgroup user_group_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_workgroup
    ADD CONSTRAINT user_group_fk FOREIGN KEY (user_id) REFERENCES public.singleuser(user_id);


--
-- TOC entry 2912 (class 2606 OID 16477)
-- Name: user_workgroup user_group_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_workgroup
    ADD CONSTRAINT user_group_fk_1 FOREIGN KEY (group_id) REFERENCES public.workgroup(group_id);


--
-- TOC entry 2909 (class 2606 OID 16446)
-- Name: workgroup workgroup_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workgroup
    ADD CONSTRAINT workgroup_fk FOREIGN KEY (group_leader) REFERENCES public.singleuser(user_id);


--
-- TOC entry 2910 (class 2606 OID 24665)
-- Name: workgroup workgroup_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workgroup
    ADD CONSTRAINT workgroup_fk_1 FOREIGN KEY (group_label) REFERENCES public.labels(label_id);


-- Completed on 2022-04-28 02:30:12

--
-- PostgreSQL database dump complete
--

