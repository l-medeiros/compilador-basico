#include <stdio.h>
int main(){
	double a;
	double b;
	double c;
	char t1[];
	char t2[];
	scanf("%lf", a);
	scanf("%lf", b);
	a = 1+2*3/b;
	t1 = "ola tudo bom"+"42";
	printf("%lf", a);
	printf("%lf", b);
	if (a>b) {
		printf("%lf", a);
	} else {
		printf("%lf", b);
	}
	if (t1==t2) {
		printf("%s", t1);
	}

	if (a==b) {
		if (t1==t2) {
			if (b==c) {
				printf("%lf", a);
			} else {
				printf("%lf", b);
			}
		}
		printf("%s", t1);
	}

	while (a==c) {
		printf("%lf", b);		if (t1!=t2) {
			printf("%s", t1);
		} else {
			scanf("%lf", a);
		}
	}
}