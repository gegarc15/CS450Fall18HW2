package edu.stlawu.montyhall;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {


    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.fragment_game, container, false);

        final ImageButton door1 = rootview.findViewById(R.id.door1);
        final ImageButton door2 = rootview.findViewById(R.id.door2);
        final ImageButton door3 = rootview.findViewById(R.id.door3);

        door1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Indicate the chosen door (Level 1)
                // And disable the other doors
                door1.setImageLevel(1);
                door1.setEnabled(false);
                door2.setEnabled(false);
                door3.setEnabled(false);

                // Set the current door
                final ImageButton current = door1;
                final ImageButton notOpened;

                // Randomly pick one of the two disabled doors
                // And show a picture of a goat
                Random rand = new Random();
                int n = rand.nextInt(2); // 0 or 1
                if(n == 0) {
                    door2.setImageLevel(3); // Door 2 = goat
                    notOpened = door3;
                } else {
                    door3.setImageLevel(3); // Door 3 = goat
                    notOpened = door2;
                }

                // Ask player if they want to switch their door
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.switch_door);
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                current.setImageLevel(0);
                                notOpened.setImageLevel(1);

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                    }
                                }, 5000);

                                // Countdown
                                final Handler cd_handler = new Handler();
                                final java.util.concurrent.atomic.AtomicInteger n = new AtomicInteger(3);
                                final Runnable counter = new Runnable() {
                                    @Override
                                    public void run() {
                                        if (n.get() == 3) {
                                            notOpened.setImageLevel(4);
                                        }
                                        if (n.get() == 2) {
                                            notOpened.setImageLevel(5);
                                        }
                                        if (n.get() == 1) {
                                            notOpened.setImageLevel(6);
                                        }

                                        if (n.getAndDecrement() >= 1) {
                                            cd_handler.postDelayed(this, 1000);
                                        } else {
                                            // Show the player what's behind the door they chose

                                            Random rand2 = new Random();
                                            int z = rand2.nextInt(2);
                                            if(z == 0) {
                                                notOpened.setImageLevel(3); // Chosen Door = car
                                                TextView win = rootview.findViewById(R.id.win_score);
                                                win.setText("1");

                                            } else {
                                                notOpened.setImageLevel(2); // Chosen Door = goat
                                                TextView loss = rootview.findViewById(R.id.loss_score);
                                                loss.setText("1");
                                            }
                                        }

                                    }
                                };
                                handler.postDelayed(counter, 1000);

                            }

                        });

                builder.setNegativeButton(R.string.no,
                        new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    return;
                                }
                            }, 5000);

                            // Countdown
                            final Handler cd_handler = new Handler();
                            final java.util.concurrent.atomic.AtomicInteger n = new AtomicInteger(3);
                            final Runnable counter = new Runnable() {
                                @Override
                                public void run() {
                                    if (n.get() == 3) {
                                        current.setImageLevel(4);
                                    }
                                    if (n.get() == 2) {
                                        current.setImageLevel(5);
                                    }
                                    if (n.get() == 1) {
                                        current.setImageLevel(6);
                                    }

                                    if (n.getAndDecrement() >= 1) {
                                        cd_handler.postDelayed(this, 1000);
                                    } else {
                                        // Show the player what's behind the door they chose

                                        Random rand2 = new Random();
                                        int z = rand2.nextInt(2);
                                        if(z == 0) {
                                            current.setImageLevel(3); // Chosen Door = car
                                            TextView win = rootview.findViewById(R.id.win_score);
                                            win.setText("1");

                                        } else {
                                            current.setImageLevel(2); // Chosen Door = goat
                                            TextView loss = rootview.findViewById(R.id.loss_score);
                                            loss.setText("1");
                                        }
                                    }

                                }
                            };
                            handler.postDelayed(counter, 1000);

                        }

                    });

                builder.show();

            }
        });

        door2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Indicate the chosen door (Level 1)
                // And disable the other doors
                door2.setImageLevel(1);
                door1.setEnabled(false);
                door2.setEnabled(false);
                door3.setEnabled(false);

                // Set the current door
                final ImageButton current = door2;
                final ImageButton notOpened;

                // Randomly pick one of the two disabled doors
                // And show a picture of a goat
                Random rand = new Random();
                int n = rand.nextInt(2); // 0 or 1
                if(n == 0) {
                    door1.setImageLevel(3); // Door 1 = goat
                    notOpened = door3;
                } else {
                    door3.setImageLevel(3); // Door 3 = goat
                    notOpened = door1;
                }

                // Ask player if they want to switch their door
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.switch_door);
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                current.setImageLevel(0);
                                notOpened.setImageLevel(1);

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                    }
                                }, 5000);

                                // Countdown
                                final Handler cd_handler = new Handler();
                                final java.util.concurrent.atomic.AtomicInteger n = new AtomicInteger(3);
                                final Runnable counter = new Runnable() {
                                    @Override
                                    public void run() {
                                        if (n.get() == 3) {
                                            notOpened.setImageLevel(4);
                                        }
                                        if (n.get() == 2) {
                                            notOpened.setImageLevel(5);
                                        }
                                        if (n.get() == 1) {
                                            notOpened.setImageLevel(6);
                                        }

                                        if (n.getAndDecrement() >= 1) {
                                            cd_handler.postDelayed(this, 1000);
                                        } else {
                                            // Show the player what's behind the door they chose

                                            Random rand2 = new Random();
                                            int z = rand2.nextInt(2);
                                            if(z == 0) {
                                                notOpened.setImageLevel(3); // Chosen Door = car
                                                TextView win = rootview.findViewById(R.id.win_score);
                                                win.setText("1");
                                            } else {
                                                notOpened.setImageLevel(2); // Chosen Door = goat
                                                TextView loss = rootview.findViewById(R.id.loss_score);
                                                loss.setText("1");
                                            }
                                        }

                                    }
                                };
                                handler.postDelayed(counter, 1000);
                            }

                        });
                builder.setNegativeButton(R.string.no,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        return;
                                    }
                                }, 5000);

                                // Countdown
                                final Handler cd_handler = new Handler();
                                final java.util.concurrent.atomic.AtomicInteger n = new AtomicInteger(3);
                                final Runnable counter = new Runnable() {
                                    @Override
                                    public void run() {
                                        if (n.get() == 3) {
                                            current.setImageLevel(4);
                                        }
                                        if (n.get() == 2) {
                                            current.setImageLevel(5);
                                        }
                                        if (n.get() == 1) {
                                            current.setImageLevel(6);
                                        }

                                        if (n.getAndDecrement() >= 1) {
                                            cd_handler.postDelayed(this, 1000);
                                        } else {
                                            // Show the player what's behind the door they chose

                                            Random rand2 = new Random();
                                            int z = rand2.nextInt(2);
                                            if(z == 0) {
                                                current.setImageLevel(3); // Chosen Door = car
                                                TextView win = rootview.findViewById(R.id.win_score);
                                                win.setText("1");
                                            } else {
                                                current.setImageLevel(2); // Chosen Door = goat
                                                TextView loss = rootview.findViewById(R.id.loss_score);
                                                loss.setText("1");
                                            }
                                        }

                                    }
                                };
                                handler.postDelayed(counter, 1000);

                            }

                        });

                builder.show();

            }
        });

        door3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Indicate the chosen door (Level 1)
                // And disable the other doors
                door3.setImageLevel(1);
                door1.setEnabled(false);
                door2.setEnabled(false);
                door3.setEnabled(false);

                // Set the current door
                final ImageButton current = door3;
                final ImageButton notOpened;

                // Randomly pick one of the two disabled doors
                // And show a picture of a goat
                Random rand = new Random();
                int n = rand.nextInt(2); // 0 or 1
                if(n == 0) {
                    door1.setImageLevel(3); // Door 1 = goat
                    notOpened = door2;
                } else {
                    door2.setImageLevel(3); // Door 2 = goat
                    notOpened = door1;
                }

                // Ask player if they want to switch their door
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.switch_door);
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                current.setImageLevel(0);
                                notOpened.setImageLevel(1);

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                    }
                                }, 5000);

                                // Countdown
                                final Handler cd_handler = new Handler();
                                final java.util.concurrent.atomic.AtomicInteger n = new AtomicInteger(3);
                                final Runnable counter = new Runnable() {
                                    @Override
                                    public void run() {
                                        if (n.get() == 3) {
                                            notOpened.setImageLevel(4);
                                        }
                                        if (n.get() == 2) {
                                            notOpened.setImageLevel(5);
                                        }
                                        if (n.get() == 1) {
                                            notOpened.setImageLevel(6);
                                        }

                                        if (n.getAndDecrement() >= 1) {
                                            cd_handler.postDelayed(this, 1000);
                                        } else {
                                            // Show the player what's behind the door they chose

                                            Random rand2 = new Random();
                                            int z = rand2.nextInt(2);
                                            if(z == 0) {
                                                notOpened.setImageLevel(3); // Chosen Door = car
                                                TextView win = rootview.findViewById(R.id.win_score);
                                                win.setText("1");
                                            } else {
                                                notOpened.setImageLevel(2); // Chosen Door = goat
                                                TextView loss = rootview.findViewById(R.id.loss_score);
                                                loss.setText("1");
                                            }
                                        }

                                    }
                                };
                                handler.postDelayed(counter, 1000);
                            }

                        });
                builder.setNegativeButton(R.string.no,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        return;
                                    }
                                }, 5000);

                                // Countdown
                                final Handler cd_handler = new Handler();
                                final java.util.concurrent.atomic.AtomicInteger n = new AtomicInteger(3);
                                final Runnable counter = new Runnable() {
                                    @Override
                                    public void run() {
                                        if (n.get() == 3) {
                                            current.setImageLevel(4);
                                        }
                                        if (n.get() == 2) {
                                            current.setImageLevel(5);
                                        }
                                        if (n.get() == 1) {
                                            current.setImageLevel(6);
                                        }

                                        if (n.getAndDecrement() >= 1) {
                                            cd_handler.postDelayed(this, 1000);
                                        } else {
                                            // Show the player what's behind the door they chose

                                            Random rand2 = new Random();
                                            int z = rand2.nextInt(2);
                                            if(z == 0) {
                                                current.setImageLevel(3); // Chosen Door = car
                                                TextView win = rootview.findViewById(R.id.win_score);
                                                win.setText("1");
                                            } else {
                                                current.setImageLevel(2); // Chosen Door = goat
                                                TextView loss = rootview.findViewById(R.id.loss_score);
                                                loss.setText("1");
                                            }
                                        }

                                    }
                                };
                                handler.postDelayed(counter, 1000);

                            }

                        });

                builder.show();

            }
        });



       return rootview;
    }


}
