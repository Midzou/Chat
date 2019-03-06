#include <iostream> 

int r = 0; //с4ет4ик для екрукрсии

using namespace std;

int Size_Width(int ** field, int i, int j, int key, int size)
{
	int size_width = 0;
	int j_ = j; //С4итаем слева от клю4а
	j = j + 1; //справа после клю4а
	if (j != size + 1) {   //т.к вылезает за пределы массива 
		while (field[i][j] == key)
		{
			if (j == size)// Если выбежали за массив
			{
				break;
			}
			size_width++;
			j = j + 1;
		}
	}
	while (field[i][j_] == key) //С клю4а
	{
		size_width++;
		j_ = j_ - 1;
		if (j_ < 0)// Если выбежали за массив
		{
			break;
		}
	}
	return size_width;
}

int Size_Height(int ** field, int i, int j, int key, int size)
{
	int size_width = 0;
	int i_ = i;//с4итаем вверх с клю4а
	i = i + 1;
	size = size - 1;
	if (i != size + 1) {   //т.к вылезает за пределы массива 
		while (field[i][j] == key)
		{
			if (i == size)
			{
				size_width++;
				break;
			}
			size_width++;
			i = i + 1;
		}
	}

	while (field[i_][j] == key)
	{
		if (i_ == 0)
		{
			size_width++;
			break;
		}
		size_width++;
		i_ = i_ - 1;
	}

	return size_width;
}

int Check_top_empty(int ** field, int i, int j, int key, int size)
{
	int sw = Size_Width(field, i, j, key, size);
	int sh = Size_Height(field, i, j, key, size);
	while (field[i][j] == key)//Находим самый верхний элемент области
	{
		if (i == 0)
		{
			i = i - 1;
			break;
		}
		i = i - 1;
	}
	i++;

	while (field[i][j] == key)//Находим самый левый элемент области
	{
		if (j == 0)
		{
			j = j - 1;
			break;
		}
		j = j - 1;
	}
	j++;

	bool empty;
	i = i - 1;
	for (int n = j; n < sw + j; n++)
	{
		if (i < 0)
		{
			return false;
		}
		if (field[i][n] != '*')
		{

			return false;
		}

	}
	return true;

}

int Check_bottom_empty(int ** field, int i, int j, int key, int size)
{
	int sw = Size_Width(field, i, j, key, size);
	int sh = Size_Height(field, i, j, key, size);
	while (field[i][j] == key)//Находим самый верхний элемент области
	{
		if (i == 0)
		{
			i = i - 1;
			break;
		}
		i = i - 1;
	}
	i++;

	while (field[i][j] == key)//Находим самый левый элемент области
	{
		if (j == 0)
		{
			j = j - 1;
			break;
		}
		j = j - 1;
	}
	j++;

	bool empty;
	for (int n = j; n < sw + j; n++)
	{
		if ((i + sh) == size)
		{
			return false;
		}
		if (field[i + sh][n] != '*')
		{

			return false;
		}

	}
	return true;

}

int Check_right_empty(int ** field, int i, int j, int key, int size)
{
	int sh = Size_Height(field, i, j, key, size);
	int sw = Size_Width(field, i, j, key, size);

	while (field[i][j] == key)//Находим самый верхний элемент области
	{
		if (i == 0)
		{
			i = i - 1;   //по аналогии с j
			break;
		}
		i = i - 1;
	}
	i++;

	while (field[i][j] == key)//Находим самый левый элемент области
	{
		if (j == 0)
		{
			j = j - 1;
			break;
		}
		j = j - 1;
	}

	j++;

	bool empty;
	for (int n = i; n < sh + i; n++)  //Бежим сверху вниз
	{
		if (field[n][j + sw] != '*')
		{
			return false;
		}

	}

	return true;

}

int Check_left_empty(int ** field, int i, int j, int key, int size)
{

	int sh = Size_Height(field, i, j, key, size);
	int sw = Size_Width(field, i, j, key, size);

	while (field[i][j] == key)//Находим самый верхний элемент области
	{
		if (i == 0)
		{
			i = i - 1;
			break;
		}
		i = i - 1;
	}
	i++;

	while (field[i][j] == key)//Находим самый левый элемент области
	{
		if (j == 0)
		{
			j = j - 1;
			break;
		}
		j = j - 1;
	}
	j++;

	j = j - 1;
	for (int n = i; n < (sh + i); n++)  //Бежим сверху вниз
	{

		if (field[n][j] != '*')
		{

			return false;
		}

	}
	return true;

}

void print(int **arr, int size)
{
	cout << endl << endl << endl;

	for (int i = 0; i < size; i++)
	{
		for (int j = 0; j < size; j++)
		{
			cout << arr[i][j] << "  ";
		}
		cout << endl << endl;
	}
	cout << endl << endl << endl;

}

void bottom(int ** field, int i_, int j_, int key, int size)
{
	int i = i_;
	int j = j_;




	while (field[i_][j] == key)//Находим самый верхний элемент области
	{
		if (i_ == 0)
		{
			i_ = i_ - 1;
			break;
		}
		i_ = i_ - 1;
	}
	i_++;


	while (field[i][j_] == key)//Находим самый левый элемент области
	{
		if (j_ == 0)
		{
			j_ = j_ - 1;
			break;
		}
		j_ = j_ - 1;
	}
	j_++;


	{
		int sh = Size_Height(field, i, j, key, size);//во избежания изменения размера 
		for (int sw = 0; sw < Size_Width(field, i, j, key, size); sw++) //Бежим по ширине клю4ей
		{
			field[i_ + sh][j_ + sw] = key;

		}
	}


}

void top(int ** field, int i_, int j_, int key, int size)
{
	int i = i_;
	int j = j_;

	while (field[i_][j] == key)//Находим самый верхний элемент области
	{
		if (i_ == 0)
		{
			i_ = i_ - 1;
			break;
		}
		i_ = i_ - 1;
	}
	i_++;

	while (field[i][j_] == key)//Находим самый левый элемент области
	{
		if (j_ == 0)
		{
			j_ = j_ - 1;
			break;
		}
		j_ = j_ - 1;
	}
	j_++;
	int sh = Size_Height(field, i, j, key, size);//во избежания изменения размера 
	i_ = i_ - 1;
	for (int sw = 0; sw < Size_Width(field, i, j, key, size); sw++) //Бежим по ширине клю4ей
	{
		field[i_][j_ + sw] = key;

	}


}

void right(int ** field, int i_, int j_, int key, int size)
{
	int i = i_;
	int j = j_;


	while (field[i_][j] == key)//Находим самый верхний элемент области
	{
		if (i_ == 0)
		{
			i_ = i_ - 1;
			break;
		}
		i_ = i_ - 1;
	}
	i_++;

	while (field[i][j_] == key)//Находим самый левый элемент области
	{
		if (j_ == 0)
		{
			j_ = j_ - 1;
			break;
		}
		j_ = j_ - 1;
	}
	j_++;


	{
		int sh = Size_Width(field, i, j, key, size);//во избежания изменения размера 
		for (int sw = 0; sw < Size_Height(field, i, j, key, size); sw++) //Бежим по высоте клю4ей
		{

			field[i_ + sw][j_ + sh] = key;

		}
	}

}

void left(int ** field, int i_, int j_, int key, int size)
{
	int i = i_;
	int j = j_;



	{
		while (field[i_][j] == key)//Находим самый верхний элемент области
		{
			if (i_ == 0)
			{
				i_ = i_ - 1;
				break;
			}
			i_ = i_ - 1;
		}
		i_++;

		while (field[i][j_] == key)//Находим самый левый элемент области
		{
			if (j_ == 0)
			{
				j_ = j_ - 1;
				break;
			}
			j_ = j_ - 1;
		}
		j_++;



		int sh = Size_Height(field, i, j, key, size);//во избежания изменения размера 
		j_ = j_ - 1;
		for (int n = i_; n < (sh + i_); n++)  //Бежим сверху вниз
		{

			field[n][j_] = key;
		}

	}

}

void recovery_field(int **field, int **primaty_field, int size)
{
	for (int k = 0; k < size; k++)  //Копируем field в primaty_field
		for (int b = 0; b < size; b++)
		{
			primaty_field[k][b] = field[k][b]; //Копируем филд

		}
}

int growth_sequence(int *grow, int size)
{
	int n = 4;
	int m = size;
	int j = m - 1;
	while (j >= 0 && grow[j] == n) j--;
	if (j < 0) return false;
	if (grow[j] >= n)
		j--;
	grow[j]++;
	if (j == m - 1) return true;
	for (int k = j + 1; k < m; k++)
		grow[k] = 1;
	return true;
}



int check_the_number_of_items(int **field, int size, int key)
{
	int count = 0;
	for (int i = 0; i < size; i++)
	{
		for (int j = 0; j < size; j++)
		{
			if (key == field[i][j])
			{
				count++;
			}
		}
	}
	if (key == count)
	{
		return 1;
	}
	else { return 0; }



}

int count_key(int**field, int size, int key)
{
	int count = 0;
	for (int i = 0; i < size; i++)
	{
		for (int j = 0; j < size; j++)
		{
			if (field[i][j] == key)
				count++;
		}
	}
	return count;
}

int get_i(int **key, int  level, int size)
{

	int count = 1;
	for (int i = 0; i < size; i++)
	{
		for (int j = 0; j < size; j++)
		{
			if (key[i][j] != '*')
			{
				if (count == level)
				{

					return i;
				}
				else { count++; }
			}

		}
	}

}//полу4аем I

int get_j(int **key, int  level, int size)
{
	int count = 1;
	for (int i = 0; i < size; i++)
	{
		for (int j = 0; j < size; j++)
		{
			if (key[i][j] != '*')
			{
				if (count == level)
				{
					return j;
				}
				else { count++; }
			}

		}
	}
}





int rec(int **field, int **key, int **primaty_field, int level)
{
	cout << "LEVEL" << level << endl;
	r++;
	int size = 5;
	print(field, size);



	int **dinamic_p = new int *[size];
	for (int v = 0; v < size; v++)
		dinamic_p[v] = new int[size];

	int **dinamic = new int *[size];
	for (int v = 0; v < size; v++)
		dinamic[v] = new int[size];


	for (int i = 0; i < size; i++)
	{
		for (int j = 0; j < size; j++)
		{
			dinamic[i][j] = field[i][j];
			dinamic_p[i][j] = primaty_field[i][j];
		}
	}

	int value = 0;
	int fuul = 0;
	int grow[5] = { 1, 1, 1, 1, 1 }; //Массив отве4ающий за рост каждого клю4а




									 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
									 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
									 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	int i = get_i(key, level, size);
	int j = get_j(key, level, size);


	{
		while (growth_sequence(grow, size) == true)
		{

			//	cout << grow[0] << " " << grow[1] << " " << grow[2] << " " << grow[3] << " " << grow[4] << " " << endl;

			value = 0;
			int test = 0;


			for (int n = 0; n < size; n++)
			{


				if (grow[n] == 1)//Вверх
				{

					if (Check_top_empty(dinamic_p, i, j, key[i][j], size) == true) {

						top(dinamic_p, i, j, key[i][j], size);
						value++;

					}

				}
				if (grow[n] == 2)//Вниз
				{
					if (Check_bottom_empty(dinamic_p, i, j, key[i][j], size) == true) {
						bottom(dinamic_p, i, j, key[i][j], size);
						value++;
					}
				}

				if (grow[n] == 3)//Вправо
				{
					if (Check_right_empty(dinamic_p, i, j, key[i][j], size) == true) {
						right(dinamic_p, i, j, key[i][j], size);
						value++;
					}
				}

				if (grow[n] == 4)//Влево
				{
					if (Check_left_empty(dinamic_p, i, j, key[i][j], size) == true) {
						left(dinamic_p, i, j, key[i][j], size);
						value++;
					}
				}
				//	cout << count_key(dinamic_p, size, key[i][j]) << endl;
				//print(dinamic_p, size);


				if (count_key(dinamic_p, size, key[i][j]) == key[i][j]) //если заполнели клю4
				{

					level = level + 1;
					rec(dinamic_p, key, dinamic_p, level);
					level = level - 1;
					recovery_field(dinamic, dinamic_p, size);
					break;
					if (key[i][j] == 6)
					{
						print(dinamic_p, size);
						return 0;
					}

				}
				//print(dinamic_p, size);
				//rec(dinamic_p, key, dinamic_p);
				//recovery_field(dinamic, dinamic_p, size);
				if (count_key(dinamic_p, size, key[i][j]) > key[i][j])
				{
					recovery_field(dinamic, dinamic_p, size);
					break;
				}


			}

		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	free(dinamic);
	free(dinamic_p);
	for (int i = 0; i < size; i++) {
		delete[]dinamic[i];
		delete[]dinamic_p[i];
	}
	delete[]dinamic_p;
	delete[]dinamic;


	//print(field, size);
	//print(key, size);
	//print(primaty_field, size);
	///cout <<"Grow : "<< grow[0] << "   " << grow[1]<<endl;


	//rec(field, key, primaty_field);   ///Рекурсия
}




int main()
{
	int size = 5;


	int **field = new int *[size];
	for (int v = 0; v < size; v++)
		field[v] = new int[size];


	int **key = new int *[size];
	for (int v = 0; v < size; v++)
		key[v] = new int[size];

	int **primaty_field = new int *[size];
	for (int v = 0; v < size; v++)
		primaty_field[v] = new int[size];


	for (int i = 0; i < size; i++) //Заполняем пустоту
	{
		for (int j = 0; j < size; j++)
		{

			field[i][j] = '*';
			key[i][j] = '*';
			primaty_field[i][j] = '*';
		}

	}

	key[0][0] = 6;
	key[0][3] = 4;
	key[2][0] = 8;
	key[2][4] = 2;
	key[4][0] = 5;



	field[0][0] = 6;
	field[0][3] = 4;
	field[2][0] = 8;
	field[2][4] = 2;
	field[4][0] = 5;


	primaty_field[0][0] = 6;
	primaty_field[0][3] = 4;
	primaty_field[2][0] = 8;
	primaty_field[2][4] = 2;
	primaty_field[4][0] = 5;

	int level = 1;

	rec(field, key, primaty_field, level);
	cout << "END" << endl;

	system("pause");
	return 0;
}


