
CREATE TABLE public.trform (
	formid serial NOT NULL,
	ename varchar NULL,
	etime varchar NULL,
	etype varchar NULL,
	edate date NULL,
	ecost int4 NULL,
	egf varchar NULL,
	pg varchar NULL,
	grade varchar NULL DEFAULT 'N\A'::character varying,
	eid int4 NULL,
	status varchar NULL,
	edescript varchar NULL,
	CONSTRAINT trform_pkey PRIMARY KEY (formid),
	CONSTRAINT trform_fk FOREIGN KEY (eid) REFERENCES users(userid)
);
CREATE TABLE public.users (
	userid serial NOT NULL,
	username varchar NULL,
	pass_word varchar NULL,
	positions varchar NULL,
	availablebalance int4 NULL DEFAULT 1000,
	pendingamount float4 NULL,
	awardamount float4 NULL,
	CONSTRAINT users_pkey PRIMARY KEY (userid)
);