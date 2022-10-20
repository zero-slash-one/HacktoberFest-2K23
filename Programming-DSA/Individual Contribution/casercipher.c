#include <stdio.h>
int caesarCipher(int n, char s[], int k)
{
    char var1;
    int var;
    for (int i = 0; i < n; i++)
    {
        if (s[i] >= 65 && s[i] <= 90)
        {
            var1 = s[i] + k;
            if (var1 > 90 || var1 > -129 && var1 < 0)
            {
                var = (k - (90 - s[i])) % 26;
                if (var)
                    var1 = var + 64;
                else
                    var1 = 90;
            }
            s[i] = var1;
        }
        if (s[i] >= 97 && s[i] <= 122)
        {
            var1 = s[i] + k;
            if (var1 > 122 || var1 > -129 && var1 < 0)
            {
                var = (k - (122 - s[i])) % 26;
                if (var)
                    var1 = var + 96;
                else
                    var1 = 122;
            }
            s[i] = var1;
        }
        printf("%c", s[i]);
    }
    return 0;
}
int main()
{
    int n, k;
    char s[100];
    scanf("%d", &n);
    scanf("%s", &s);
    scanf("%d", &k);
    caesarCipher(n, s, k);
    return 0;
}