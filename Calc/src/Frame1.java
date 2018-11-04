package Calc_RGR;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;

import javax.swing.SwingConstants;

import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class Frame1 {
	//инициируем переменные и элементы формы
	private JFrame frame;
	private JTextArea textArea;
	int h,w,fps,hours,cam,days;
	double deep,form,razv;
	@SuppressWarnings("rawtypes") //комбобоксы выдают ворнинг на то, что комбобоксы имеют дженерик тайп, но фактически на работу программы не влияет
	static JComboBox[] ComboBoxes = new JComboBox[3];
	static JSpinner spinner_1 = new JSpinner();
    static JTextField[] textFields = new JTextField[5];
	
    //вызов фрейма программы
	public static void main(String[] args) { //в основном при разработке приложений советуют выполнять запуск программы отдельным потоком
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	};
	
	public Frame1() {
		initialize(); //инициализируем выполнение фрейма и его элементов
	}
	
	@SuppressWarnings("unchecked")
	public void initialize() {
		Font for_label=new Font ("Tahoma", Font.PLAIN, 20);
		Font for_text=new Font ("Dialog", Font.PLAIN, 16);

		frame = new JFrame();
		frame.setFont(for_label);
		frame.setTitle("Расчёт памяти для камер видеонаблюдения");
		frame.setResizable(false);
		frame.setBounds(100, 100, 500, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Выполняем установку типовых лейблов в цикле, массивом задается содержание лейбла
		String[] label_name = {"Высота", "Ширина", "Глубина цвета", "FPS", "Формат", "Развёртка", "Количество камер", "Часов в день", "Количество дней"};
		JLabel[] labels = new JLabel[9];
        for (int i = 0; i < 9; i++) {
        	labels[i] = new JLabel();
        	labels[i].setText(label_name[i]);
        	labels[i].setHorizontalAlignment(SwingConstants.RIGHT);
        	labels[i].setFont(for_label);
        	labels[i].setBounds(10, 20+35*i, 225, 25);
        	frame.getContentPane().add(labels[i]);
        }
		
        //Выполняем установку типовых форм ввода в цикле, массивом задается содержание формы
        String[] text_name = {"1050", "1680", "20", "1", "1"};
        int[] y_position_fields = {20, 55, 125, 230, 300};
        for (int i = 0; i < 5; i++) {
        	textFields[i]= new JTextField();  
        	textFields[i].setText(text_name[i]);
        	textFields[i].setFont(for_text);
        	textFields[i].setBounds(250, y_position_fields[i], 225, 25);
        	frame.getContentPane().add(textFields[i]);
        	textFields[i].setColumns(10);
        	check_wrong_enter(i);
        }
   
        //здесь у нас содержатся массивы наполнения всех трёх комбобоксов
        double[] form_value = {0.769, 0.37, 0.333, 0.222, 0.149, 0.133, 0.132, 0.116, 0.11, 0.1, 0.065, 0.065, 0.047, 0.043, 0.04};
        String[] form_name = {"HDCAM SR HQ", "HDCAM SR SQ", "Pure YCbCr 4:2:2", "Prores444", "Prores422", "REDCODE 42 Codec", "PEG2000 250Mbps for 2Kp24", "DVCPRO HD 4:2:2 Y'CbCr", "REDCODE 36 Codec", "REDCODE 28 Codec", "JPEG2000 250Mbps for 2Kp48", "MPEG2 High", "DVD MPEG2", "BluRay H.264", "DVCPRO50"};
        float[] razv_value = {1, (float) 0.5};
        String[] razv_name = {"Прогрессивный", "Чересстрочный"};
        int[] deep_value = {8, 10, 12, 16, 24, 30, 36};
        String[] deep_name = {"RAW 8bit", "RAW 10bit", "RAW 12bit", "RAW 16bit", "RGB 3x8bit", "RGB 3x10bit", "RGB 3x12bit"};
        int[] y_position_boxes = {90, 160, 195};

        //в цикле заполняем содержанием все три комбобокса, создавая новые объекты Item
        for (int i = 0; i < 3; i++) {
        	ComboBoxes[i] = new JComboBox<Object>();
        	ComboBoxes[i].setFont(for_text);
        	if(i==0) {
        		for (int j = 0; j < 7; j++) {
        	        ComboBoxes[i].addItem(new Item(deep_value[j], deep_name[j]));
        		}
        	}
        	if(i==1) {
        		for (int j = 0; j < 14; j++) {
                    ComboBoxes[i].addItem(new Item(form_value[j], form_name[j]));
        		}
            }
        	if(i==2) {
        		for (int j = 0; j < 2; j++) {
        			ComboBoxes[i].addItem(new Item(razv_value[j], razv_name[j]));
        		}
        	}
        	ComboBoxes[i].setBounds(250, y_position_boxes[i], 225, 25);
        	frame.getContentPane().add(ComboBoxes[i]);
        }    
		//задаём параметры спиннера	
		spinner_1.setModel(new SpinnerNumberModel(1, 1, 24, 1));
		spinner_1.setFont(for_text);
		spinner_1.setBounds(250, 264, 225, 26);
		frame.getContentPane().add(spinner_1);
		((JSpinner.DefaultEditor)spinner_1.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.LEFT);
		((JSpinner.DefaultEditor)spinner_1.getEditor()).getTextField().addKeyListener(new KeyAdapter() {
			   public void keyTyped(KeyEvent e) { //
	 			      char c = e.getKeyChar();
	 			      if ( (c < '0') || (c > '9')) {
	 			         e.consume();
	 			      }
	 			   }
	 			});
		//инициируем зону вывода
		textArea = new JTextArea();
		textArea.setBackground(SystemColor.menu);
		textArea.setColumns(1);
		textArea.setEditable(false);
		textArea.setFont(for_text);
		textArea.setBounds(250, 345, 225, 130);
		textArea.setBorder(BorderFactory.createLoweredBevelBorder());
		frame.getContentPane().add(textArea);
		textArea.setRows(6);
		
		//инициируем кнопку исполнения кода
		JButton button = new JButton();
		button.setIcon(new ImageIcon("src/images/button.png"));
		button.setForeground(SystemColor.menu);
		button.setFont(for_label);
		button.setBounds(62, 345, 130, 130);
		frame.getContentPane().add(button);
		
		//слушатель кнопки исполняет метод рассчётов и вывода результатов
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				print_answer();
			}
		});
	};
	//метод инициирующий слушатель на каждое нажатие кнопки клавиатуры и в случае, если полученное значение символа юникода не входит в заданный диапазон, то отменяет ввод символа
	public void check_wrong_enter(int position) {
		textFields[position].addKeyListener(new KeyAdapter() {
			   public void keyTyped(KeyEvent e) {
			      char c = e.getKeyChar();
			      if ( ((c < '0') || (c > '9'))) {
			         e.consume(); 
			      }
			   }
			});
	}
	
	//класс, позволяющий нам создавать и считывать значение объектов, которыми мы заполнили комбобоксы
	public class Item {		
		double value;
		String Name;
		 public Item(double value, String Name)
		    {
		        this.value = value;
		        this.Name = Name;
		    }
		 @Override
		    public String toString() //устанавливает корректный вывод строкового значения в комбобокс
		    {
		        return Name;
		    }
		 public double getvalue() { //извлекает коэффициент из объекта Item
			 return value;
		 }
	}
	
	//метод рассчитывает количество битов за час
	public static double end_calc(int h, int w, double deep, int fps, double form, double razv, int cam, int hours, int days) {
		double answer = (h*w*deep*fps*form*razv*cam*3600*hours*days);
	return answer;
	}
	//метод, который мы используем для конвертации в высшую форму записи
	public static double convert_to_higher(double x, int y) {
		double converted = x/y;
		return converted;
	}
	//метод рассчёта и вывода
	public void print_answer() {
		/*делаем попытку присвоить значение, заданное в окнах формы в переменные, а так же обрабатываем ошибку
		 *  в случае если окно содержит null (NumberFormatException так же вызывался бы в случае, если был бы возможен ввод
		 *  в окно буквенных значений)
		 */
		try {
			h = Integer.parseInt(textFields[0].getText());
			w = Integer.parseInt(textFields[1].getText());
			fps = Integer.parseInt(textFields[2].getText());
			cam = Integer.parseInt(textFields[3].getText());
			days = Integer.parseInt(textFields[4].getText());
			hours = (Integer) spinner_1.getValue();
			deep = ((Item) ComboBoxes[0].getSelectedItem()).getvalue();
			form = ((Item) ComboBoxes[1].getSelectedItem()).getvalue();
			razv = ((Item) ComboBoxes[2].getSelectedItem()).getvalue();
			} catch (NumberFormatException exception) {
				//предупреждение пользователя о невозможности выполнения дальнейших действий
			JOptionPane.showMessageDialog(frame, "Одно или несколько полей пусты: Пожалуйста, вернитесь и введите значение", "Ошибка", JOptionPane.ERROR_MESSAGE);
			return;
		}					
		
		double form_answer = end_calc(h,w,deep,fps,form,razv,cam,hours,days); //считаем биты с данными значениями
		int[] del = {8, 1024, 1024, 1024, 1024};
		String[] name = {"B", "KB", "MB", "GB", "TB"};
		
		/*в цикле рассчитываем биты в байты и последующие вышестоящие единицы измерения вплоть до террабайтов,
		 *  форматируем их вывод в зависимости от того насколько мала полученная переменная и выводим в jtextarea*/
		textArea.setText(String.format("%.0f",form_answer)+" bit");
		for(int i = 0; i < 5; i++){
			form_answer = convert_to_higher(form_answer,del[i]);
			if (form_answer>=0.0001)	{textArea.append("\n" +String.format("%.4f",form_answer)+ " " +name[i]);}
			else if (form_answer>=0.00001) {textArea.append("\n" +String.format("%.5f",form_answer)+ " " +name[i]);}
			else if (form_answer>=0.000001) {textArea.append("\n" +String.format("%.6f",form_answer)+ " " +name[i]);}
			else if (form_answer>=0.0000001) {textArea.append("\n" +String.format("%.7f",form_answer)+ " " +name[i]);}
			else if (form_answer>=0.00000001) {textArea.append("\n" +String.format("%.8f",form_answer)+ " " +name[i]);}
			else if (form_answer>=0.000000001) {textArea.append("\n" +String.format("%.9f",form_answer)+ " " +name[i]);}
			}
		}